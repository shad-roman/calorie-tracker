package com.example.calorie_tracker.services;

import com.example.calorie_tracker.exceptions.UserNotFoundException;
import com.example.calorie_tracker.model.Goal;
import com.example.calorie_tracker.model.Sex;
import com.example.calorie_tracker.model.Users;
import com.example.calorie_tracker.repositories.DishRepository;
import com.example.calorie_tracker.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, DishRepository dishRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id){
        Optional<Users> user =  userRepository.findById(id);
        if(user.isPresent()){
            return user;
        } else {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }

    public Users createUser(Users user){
        if (user.getWeight() <= 0 || user.getHeight() <= 0) {
            throw new IllegalArgumentException("Weight and Height are less then 0");
        }
        return userRepository.save(user);
    }

    public Users updateUser(Long id, Users updatedUser) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            user.setFullName(updatedUser.getFullName());
            user.setAge(updatedUser.getAge());
            user.setEmail(updatedUser.getEmail());
            user.setGoal(updatedUser.getGoal());
            user.setHeight(updatedUser.getHeight());
            user.setWeight(updatedUser.getWeight());
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public double calcDailyCalRate(Users user) {
        if (user.getGoal() == Goal.LOSE) {
            return getBMR(user) * 1.2;
        } else if (user.getGoal() == Goal.MAINTAIN) {
            return getBMR(user) * 1.5;
        } else {
            return getBMR(user) * 1.8;
        }
    }

    private double getBMR(Users user) {
        if (user.getAge() < 12) {
            throw new IllegalArgumentException("Age should be 12 or older");
        }
        if (user.getSex() == Sex.FEMALE){
            return 655.1 + (9.563 * user.getWeight()) + (1.85 * user.getHeight()) - (4.676 * user.getAge());
        } else {
            return 66.5 + (13.75 * user.getWeight()) + (5.003 * user.getHeight()) - (6.775 * user.getAge());
        }
    }
}
