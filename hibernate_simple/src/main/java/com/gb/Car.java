package com.gb;

@Table(title = "cars")
public class Car {
    @Column
    String model;

    @Column
    String number;

    @Column
    int height;

    @Column
    int year;

    @Column
    String color;

    public Car(String model, String number, int height, int year, String color) {
        this.model = model;
        this.number = number;
        this.height = height;
        this.year = year;
        this.color = color;
    }
}
