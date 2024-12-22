package com.example.smartestate.controllers;

import com.example.smartestate.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin";
    }
}
