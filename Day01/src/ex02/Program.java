package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList school = new UsersArrayList();
        school.addUser(new ex02.User("Maxim", 20000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));
        school.addUser(new User("Dasha", 100000));

        System.out.println(school.getUserByIndex(0));
        System.out.println(school.getUserByIndex(1));
        System.out.println(school.getUserByIndex(2));
        System.out.println(school.getUserByIndex(3));
        System.out.println(school.getUserByIndex(4));
        System.out.println(school.getUserByIndex(5));
        System.out.println(school.getUserByIndex(6));
        System.out.println(school.getUserByIndex(7));
        System.out.println(school.getUserByIndex(8));
        System.out.println(school.getUserByIndex(9));
        System.out.println(school.getUserByIndex(10));
        System.out.println(school.getUserById(188));


        System.out.println(school.getUserById(1));
        System.out.println(school.getUserById(2));
        System.out.println(school.getCountUsers());
    }
}
