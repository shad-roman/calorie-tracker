package com.example.calorie_tracker.repositories;

import com.example.calorie_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
