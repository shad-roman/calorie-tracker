package com.example.calorie_tracker.services;

import com.example.calorie_tracker.exceptions.DishNotFoundException;
import com.example.calorie_tracker.model.Dish;
import com.example.calorie_tracker.repositories.DishRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    public Dish getById(Long id){
        return dishRepository.findById(id).
                orElseThrow(() -> new DishNotFoundException("Dish with id " + id + " not found"));
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Transactional
    public Dish addDish(Dish dish){
        return dishRepository.save(dish);
    }

}
