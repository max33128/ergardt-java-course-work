package com.example.smartestate.controllers;

import com.example.smartestate.models.Transaction;
import com.example.smartestate.models.User;
import com.example.smartestate.repositories.UserRepository;
import com.example.smartestate.services.TransactionService;
import com.example.smartestate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final UserRepository userRepository;

    public TransactionController(TransactionService transactionService, UserService userService, UserRepository userRepository) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/transactions/add")
    public String createTransaction(Transaction transaction, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        transactionService.createTransaction(transaction);
        return "redirect:/transactions";
    }

    @PostMapping("/transactions/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
        return "redirect:/transactions";
    }
}
