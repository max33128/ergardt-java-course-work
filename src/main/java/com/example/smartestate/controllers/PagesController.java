package com.example.smartestate.controllers;

import com.example.smartestate.services.ApplicationService;
import com.example.smartestate.services.RealEstateObjectService;
import com.example.smartestate.services.TransactionService;
import com.example.smartestate.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PagesController {

    private final UserService userService;
    private final RealEstateObjectService realEstateObjectService;
    private final ApplicationService applicationService;
    private final TransactionService transactionService;

    public PagesController(UserService userService, RealEstateObjectService realEstateObjectService, ApplicationService applicationService, TransactionService transactionService) {
        this.userService = userService;
        this.realEstateObjectService = realEstateObjectService;
        this.applicationService = applicationService;
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main-page";
    }

    @GetMapping("/real-estate-objects")
    public String objectPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("objects", realEstateObjectService.list());
        return "real-estate-objects";
    }

    @GetMapping("/applications")
    public String applicationPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("applications", applicationService.list());
        return "applications";
    }

    @GetMapping("/clients")
    public String clientPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("clients", userService.list(null));
        return "clients";
    }

    @GetMapping("/transactions")
    public String transactionPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("transactions", transactionService.list());
        return "transactions";
    }

    @GetMapping("/admin")
    public String adminPage(@RequestParam(name = "chosenRole", required = false) String chosenRole, Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("users", userService.list(null));
        return "admin";
    }

}
