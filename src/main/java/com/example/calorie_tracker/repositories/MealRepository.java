package com.example.calorie_tracker.repositories;

import com.example.calorie_tracker.model.Meal;
import com.example.calorie_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import java.time.LocalDate;

public interface MealRepository  extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndDate(Long userId, LocalDate date);
    List<Meal> findByUserId (Long userId);
}
