package com.example.calorie_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Users {
    public Users() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String fullName;

    private String email;

    @Min(12)
    @Max(120)
    private Integer age;

    @Min(30)
    @Max(250)
    private Integer weight;

    @Min(50)
    @Max(250)
    private Integer height;

    private Goal goal;

    private Sex sex;

    public Users(long id, String fullName, String email, Integer age, Integer weight, Integer height, Goal goal, Sex sex) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.sex = sex;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public Goal getGoal() {
        return goal;
    }
}
