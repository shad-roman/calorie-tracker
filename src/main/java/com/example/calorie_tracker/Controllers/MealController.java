package com.example.calorie_tracker.Controllers;

import com.example.calorie_tracker.exceptions.UserNotFoundException;
import com.example.calorie_tracker.model.Meal;
import com.example.calorie_tracker.model.Users;
import com.example.calorie_tracker.services.MealService;
import com.example.calorie_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    private final  UserService userService;

    @Autowired

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }


    @GetMapping("/{userId}/report/{date}")
    public ResponseEntity<String> getReportByDate (@PathVariable Long userId,@PathVariable String date){
        LocalDate currDate = LocalDate.parse(date);
        List<Meal> list = mealService.getMealsFroDay(userId, currDate);

        double cals = 0.0;

        for (Meal meal : list){
            cals += meal.getTotalCals();
        }

        Users user = userService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + "  is not found"));

        boolean calsRate = cals <= userService.calcDailyCalRate(user);

        return ResponseEntity.ok("Total calories: " + cals + " Goal: "+
                userService.calcDailyCalRate(user) +  ", Within goal: " + calsRate);
    }

    @GetMapping("/{userId}/report/history")
    public ResponseEntity<List<Meal>> getMealHistory(@PathVariable Long userId){
        List<Meal> list = mealService.getMealHistory(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/users/{userId}/meals")
    public ResponseEntity<Meal> addMeal(
            @PathVariable Long userId,
            @RequestBody List<Long> dishIds) {
        Meal newMeal = mealService.addMeal(userId, dishIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMeal);
    }
}
