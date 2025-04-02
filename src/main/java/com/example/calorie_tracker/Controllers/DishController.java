package com.example.calorie_tracker.Controllers;

import com.example.calorie_tracker.exceptions.DishNotFoundException;
import com.example.calorie_tracker.model.Dish;
import com.example.calorie_tracker.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes(){
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getById (@PathVariable Long id){
        return dishService.getById(id)
                .map(dish -> ResponseEntity.ok(dish))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Dish> addNewTask(@RequestBody Dish dish){
        Dish addedDish = dishService.addDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDish);
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFound(DishNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
