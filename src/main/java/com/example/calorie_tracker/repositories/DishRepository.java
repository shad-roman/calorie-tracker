package com.example.calorie_tracker.repositories;

import com.example.calorie_tracker.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
