package org.school_21;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Classes:\n\t- User\n\t- Car");
        System.out.println("---------------------");
        System.out.println("Enter class name:");
        String nameClass = scanner.nextLine();

        Field[] fields = null;
        Method[] methods = null;

        if (nameClass.equals("User")) {
            fields = User.class.getDeclaredFields();
            methods = User.class.getDeclaredMethods();
            String firstName = "";
            String lastName = "";
            int height;
            System.out.println("fields:");
            for (Field field : fields) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
            }
            System.out.println("methods:");
            for (Method method : methods) {
                System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
            System.out.println("Let’s create an object.\n" +
                    "firstName:");
            firstName = scanner.nextLine();
            System.out.println("lastName:");
            lastName = scanner.nextLine();
            System.out.println("height:");
            height = scanner.nextInt();
            scanner.nextLine();
            User user = new User(firstName, lastName, height);
            System.out.println("Object created: User[firstName='" + firstName + "', lastName='"
            + lastName + "', height=" + height + "]");
            System.out.println("---------------------");
            System.out.println("Enter name of the method for call:");
            String nameMethod = scanner.nextLine();
            System.out.println("Enter int value:");
            int value = scanner.nextInt();
            System.out.println("Method returned:");
            System.out.println(user.getClass().getDeclaredMethod(nameMethod, int.class).invoke(user, value));
        } else if (nameClass.equals("Car")) {
            fields = Car.class.getDeclaredFields();
            methods = Car.class.getDeclaredMethods();
            String brand = "";
            String model = "";
            int year;
            System.out.println("fields:");
            for (Field field : fields) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
            }
            System.out.println("methods:");
            for (Method method : methods) {
                System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
            System.out.println("Let’s create an object.\n" +
                    "brand:");
            brand = scanner.nextLine();
            System.out.println("model");
            model = scanner.nextLine();
            System.out.println("year");
            year = scanner.nextInt();
            scanner.nextLine();
            Car car = new Car(brand, model, year);
            System.out.println("Object created: User[brand='" + brand + "', model='"
                    + model + "', year=" + year + "]");
            System.out.println("---------------------");
            System.out.println("Enter name of the method for call:");
            String nameMethod = scanner.nextLine();
            System.out.println("Enter int value:");
            int value = scanner.nextInt();
            System.out.println("Method returned:");
            System.out.println(car.getClass().getDeclaredMethod(nameMethod, int.class).invoke(car, value));
        }


    }
}
