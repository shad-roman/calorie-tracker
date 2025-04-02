package com.example.calorie_tracker.Controllers;

import com.example.calorie_tracker.exceptions.UserNotFoundException;
import com.example.calorie_tracker.model.Users;
import com.example.calorie_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService usersService;

    @Autowired
    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById (@PathVariable Long id){
        return usersService.getUserById(id)
                .map(users -> ResponseEntity.ok(users))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        if (usersService.deleteUser(id)){
            return ResponseEntity.noContent().build();
        }  else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users user = usersService.createUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user){
        return usersService.updateUser(id, user);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/dailyRate")
    public ResponseEntity<Double> getDailyCalorieIntake(@PathVariable Long id) {
        try {
            Optional<Users> user = usersService.getUserById(id);
            if (user.isPresent()){
                double dailyRate = usersService.calcDailyCalRate(user.get());
                return new ResponseEntity<>(dailyRate, HttpStatus.OK);
            }
            else return null;
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
