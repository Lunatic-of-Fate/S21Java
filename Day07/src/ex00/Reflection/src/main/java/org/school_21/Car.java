package org.school_21;

import java.util.StringJoiner;

public class Car {
    private final String brand;
    private final String model;
    private int year;

    public Car() {
        this.brand = "Бренд";
        this.model = "Модель";
        this.year = 2000;
    }

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public int updateYear(int newYear) {
        this.year = newYear;
        return newYear;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add("model='" + model + "'")
                .add("year=" + year)
                .toString();
    }
}