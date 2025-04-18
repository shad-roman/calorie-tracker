package com.example.calorie_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToMany
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

    public Users getUser() {
        return user;
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

    public void setUser(Users user) {
        this.user = user;
    }

    public void setListOfDishes(List<Dish> listOfDishes) {
        this.listOfDishes = listOfDishes;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
