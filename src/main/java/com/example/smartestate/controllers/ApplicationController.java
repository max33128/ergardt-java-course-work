package com.example.smartestate.controllers;

import com.example.smartestate.models.Application;
import com.example.smartestate.models.User;
import com.example.smartestate.repositories.UserRepository;
import com.example.smartestate.services.ApplicationService;
import com.example.smartestate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;
    private final UserRepository userRepository;

    public ApplicationController(ApplicationService applicationService, UserService userService, UserRepository userRepository) {
        this.applicationService = applicationService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/applications/add")
    public String createApplication(Application application, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        applicationService.createApplication(application, user);
        return "redirect:/applications";
    }

    @PostMapping("/application/edit")
    public String editApplication(@ModelAttribute Application application, Model model) {
        applicationService.editApplication(application);
        return "redirect:/applications";
    }

    @PostMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteById(id);
        return "redirect:/applications";
    }
}
