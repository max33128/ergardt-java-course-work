package com.example.smartestate.services;

import com.example.smartestate.models.User;
import com.example.smartestate.models.enums.Role;
import com.example.smartestate.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user) {
        String userEmail = user.getEmail();
        if (userRepository.findByEmail(userEmail) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getChosenRole().equals("customer")) {
            user.getRoles().add(Role.CUSTOMER);
        } else if (user.getChosenRole().equals("developer")) {
            user.getRoles().add(Role.DEVELOPER);
        } else {
            user.getRoles().add(Role.AGENT);
        }
        userRepository.save(user);
        return true;
    }

    public List<User> list(String chosenRole) {
        if (chosenRole != null) return userRepository.findByChosenRole(chosenRole);
        return userRepository.findAll();
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public void updateUserRole(Long userId, String newRole) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HashSet<Role> newRoles = new HashSet<>();
            if (newRole.equals("ADMIN")) {
                newRoles.add(Role.ADMIN);
                user.setRoles(newRoles);
                userRepository.save(user);
            } else if (newRole.equals("AGENT")) {
                newRoles.add(Role.AGENT);
                user.setRoles(newRoles);
                userRepository.save(user);
            } else if (newRole.equals("CUSTOMER")) {
                newRoles.add(Role.CUSTOMER);
                user.setRoles(newRoles);
                userRepository.save(user);
            } else if (newRole.equals("DEVELOPER")) {
                newRoles.add(Role.DEVELOPER);
                user.setRoles(newRoles);
                userRepository.save(user);
            }
        } else {
            throw new RuntimeException("Пользователь с id " + userId + " не найден");
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Long findIdByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user.getId();
    }

}
