package com.example.calorie_tracker.model;

import jakarta.persistence.*;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dish_id;

    private String name;

    private double calories;

    private double protein;

    private double fat;

    private double carbohydrates;

    public Dish(long dish_id, String name, double calories, double protein, double fat, double carbohydrates) {
        this.dish_id = dish_id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    public Dish() {
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setDish_id(long dish_id) {
        this.dish_id = dish_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public long getDish_id() {
        return dish_id;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }
}
