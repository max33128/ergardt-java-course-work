package com.example.smartestate.repositories;

import com.example.smartestate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByChosenRole(String chosenRole);
}
