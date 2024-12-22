package com.example.smartestate.controllers;

import com.example.smartestate.models.User;
import com.example.smartestate.repositories.UserRepository;
import com.example.smartestate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/first-reg")
    public String firstReg() {
        return "first-reg";
    }

    @PostMapping("/first-reg")
    public String createFirstData(User user, HttpSession session) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam(value = ("passport"), required = false) Long passport,
                             @RequestParam(value = ("inn"), required = false) Long inn_number,
                             @RequestParam(value = ("chosenRole"), required = false) String isOnlyPrimal,
                             @RequestParam(value = ("licenseNumber"), required = false) Long licenseNumber,
                             Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        if (passport != null) {
            user.setPassportNumber(passport);
        }
        if (inn_number != null) {
            user.setInnNumber(inn_number);
            if (isOnlyPrimal.equals("true")) {
                user.setOnlyPrimal(true);
            } else {
                user.setOnlyPrimal(false);
            }
        }
        if (licenseNumber != null) {
            user.setLicenseNumber(licenseNumber);
        }
        user.setFilledProfile(true);
        userRepository.save(user);
        return "redirect:/";
    }

}
