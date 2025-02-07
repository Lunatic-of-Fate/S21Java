package edu.school21.annotations;


public class Program {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String name = "melanief";
        String password = "Vfrcbv159753..";

        OrmManager om = new OrmManager(url, name, password);

        User user = new User(1L, "Andrey", "password", 30);
        User max = new User(2L, "maxim", "zhuravlev", 24);
        User kolya = new User(3L, "kolya", "lastName", 22);

        User update = new User(1L, "new", "new", 0);

        om.save(user);
        om.save(max);
        om.save(kolya);

        om.update(update);
        System.out.println(om.findById(1L, User.class));
        om.dropTable(User.class);

    }
}
