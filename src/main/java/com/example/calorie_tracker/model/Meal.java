package com.example.calorie_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany
    @JoinTable(
            name = "meal_list_of_dishes",
            joinColumns = @JoinColumn(name = "meal_id"),  // это будет ссылаться на meal.id
            inverseJoinColumns = @JoinColumn(name = "dish_id") // это будет ссылаться на dish.dish_id
    )
    private List<Dish> listOfDishes;

    private Double totalCals;

    private LocalDate date;

    public Double getTotalCals() {
        return totalCals;
    }

    public void setTotalCals(Double totalCals) {
        this.totalCals = totalCals;
    }

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return userId;
    }

    public List<Dish> getListOfDishes() {
        return listOfDishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setListOfDishes(List<Dish> listOfDishes) {
        this.listOfDishes = listOfDishes;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
