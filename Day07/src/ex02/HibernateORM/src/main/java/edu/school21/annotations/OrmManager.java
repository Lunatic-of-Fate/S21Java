package edu.school21.annotations;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrmManager {
    private final Connection connection;
    private final static String packageAnnotationName = "edu.school21.annotations";


    public OrmManager(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            for (Class<?> currClass : this.getAllClassesFrom()) {
                createTable(currClass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable(Class<?> aClass) {
        StringBuilder query =
                new StringBuilder(STR."CREATE TABLE IF NOT EXISTS \{aClass.getAnnotation(OrmEntity.class)
                .nameTable()}");
        Field[] fields = aClass.getDeclaredFields();
        query.append("(");
        for (Field field : fields) {
            if (field.isAnnotationPresent(OrmColumnId.class)) {
                query.append("id BIGSERIAL PRIMARY KEY, ");
            } else if (field.isAnnotationPresent(OrmColumn.class)) {
                OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                if (field.getType().getSimpleName().equals("String")) {
                    query.append(STR."\{ormColumn.name()} VARCHAR(\{ormColumn.length()}), ");
                } else if (field.getType().getSimpleName().equals("Integer")) {
                    query.append(STR."\{ormColumn.name()} INTEGER, ");
                } else if (field.getType().getSimpleName().equals("Double")) {
                    query.append(STR."\{ormColumn.name()} DOUBLE PRECISION, ");
                } else if (field.getType().getSimpleName().equals("Boolean")) {
                    query.append(STR."\{ormColumn.name()} BOOLEAN, ");
                } else if (field.getType().getSimpleName().equals("Long")) {
                    query.append(STR."\{ormColumn.name()} BIGINT, ");
                }
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(");");
        try {
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private List<Class<?>> getAllClassesFrom() {
        return new Reflections(packageAnnotationName, new SubTypesScanner(false))
                .getSubTypesOf(Object.class)
                .stream()
                .filter(aClass -> aClass.isAnnotationPresent(OrmEntity.class))
                .collect(Collectors.toList());
    }

    public void save(Object entity) {
        String tableName = entity.getClass().getAnnotation(OrmEntity.class).nameTable();
        List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumn.class))
                .toList();
        String fieldName = fields.stream().map(field -> field.getAnnotation(OrmColumn.class).name())
                .collect(Collectors.joining(", "));
        String placeholder = fields.stream().map(_ -> "?")
                .collect(Collectors.joining(", "));
        String query = STR."INSERT INTO \{tableName}(\{fieldName}) VALUES (\{placeholder})";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                field.setAccessible(true);
                preparedStatement.setObject(i + 1, field.get(entity));
            }
            preparedStatement.executeUpdate();
            System.out.println(query);
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                setIdToEntity(entity, id);
            }
        } catch (SQLException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setIdToEntity(Object entity, Long id) {
        Field idField = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumnId.class))
                .findFirst()
                .orElse(null);
        try {
            if (idField != null) {
                idField.setAccessible(true);
                idField.set(entity, id);
            }
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String update(Object entity) {
        String tableName = entity.getClass().getAnnotation(OrmEntity.class).nameTable();

        List<Field> fields = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumn.class))
                .toList();
        String fieldName = fields.stream()
                .map(field -> field.getAnnotation(OrmColumn.class).name())
                .collect(Collectors.joining(", "));
        try {
            String placeholder = fields.stream().peek(field -> field.setAccessible(true)).map(field -> {
                try {return field.get(entity).toString();} catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.joining("', '"));
            String query = (STR."UPDATE \{tableName} SET (\{fieldName}) = ('\{placeholder}') WHERE id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Field idField = Arrays.stream(entity.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(OrmColumnId.class))
                    .findFirst()
                    .orElse(null);
            if (idField != null) {
                idField.setAccessible(true);
                preparedStatement.setObject(1, idField.get(entity));
            }
            preparedStatement.executeUpdate();
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public <T> T findById(Long id, Class<T> aClass) {
        String tableName = aClass.getAnnotation(OrmEntity.class).nameTable();

        String query = STR."SELECT * FROM \{tableName} WHERE id = \{id}";
        T entity = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                entity = createEntityFromResultSet(aClass, resultSet);
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return entity;
    }
    private <T> T createEntityFromResultSet(Class<T> aClass, ResultSet rs) throws Exception {
        Constructor<T> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        T entity = constructor.newInstance();
        Field idField = Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumnId.class))
                .findFirst().orElse(null);
        if (idField != null) {
            idField.setAccessible(true);
            idField.set(entity, rs.getLong("id"));
        }
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(OrmColumn.class))
                .toList();

        for (Field f : fields) {
            f.setAccessible(true);
            String columnName = f.getAnnotation(OrmColumn.class).name();
            Object value = rs.getObject(columnName);
            f.set(entity, value);
        }
        return entity;
    }
    public void dropTable(Class<?> aClass) {
        String tableName = aClass.getAnnotation(OrmEntity.class).nameTable();
        try {
            PreparedStatement preparedStatement  = connection.prepareStatement(STR."DROP TABLE IF EXISTS \{tableName};");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}