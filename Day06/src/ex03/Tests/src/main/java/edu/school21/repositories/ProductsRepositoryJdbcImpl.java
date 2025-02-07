package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> arrProduct = new ArrayList<Product>();
        String query = "SELECT * FROM product";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                arrProduct.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return arrProduct;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = new Product();

        String query = "SELECT * FROM product WHERE id = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return Optional.of(product);
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE product SET id = ?, name = ?, price = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setLong(4, product.getId());
            ps.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void save(Product product) {
        String query = "INSERT INTO product VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM product WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
