package com.example.calorie_tracker.services;

import com.example.calorie_tracker.exceptions.UserNotFoundException;
import com.example.calorie_tracker.model.Dish;
import com.example.calorie_tracker.model.Meal;
import com.example.calorie_tracker.model.Users;
import com.example.calorie_tracker.repositories.DishRepository;
import com.example.calorie_tracker.repositories.MealRepository;
import com.example.calorie_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MealService {

    private final DishRepository dishRepository;

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    @Autowired

    public MealService(DishRepository dishRepository, MealRepository mealRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    public List<Meal> getMealHistory (Long userId){
        return mealRepository.findByUserId(userId);
    }

    public List<Meal> getMealsFroDay (Long userId, LocalDate date){
        return mealRepository.findByUserIdAndDate(userId, date);
    }

    public Meal addMeal (Long userId, List<Long> dishIds){
        Users user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));


        List<Dish> list = dishRepository.findAllById(dishIds);

        Double totalCals = 0.0;
        for (Dish dish : list){
            totalCals += dish.getCalories();
        }

        Meal meal = new Meal();
        meal.setDate(LocalDate.now());
        meal.setUserId(userId);
        meal.setListOfDishes(list);
        meal.setTotalCals(totalCals);

        return mealRepository.save(meal);
    }
}
