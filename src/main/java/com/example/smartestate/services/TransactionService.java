package com.example.smartestate.services;

import com.example.smartestate.models.RealEstateObject;
import com.example.smartestate.models.Transaction;
import com.example.smartestate.models.User;
import com.example.smartestate.repositories.RealEstateObjectRepository;
import com.example.smartestate.repositories.TransactionRepository;
import com.example.smartestate.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final RealEstateObjectRepository realEstateObjectRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, RealEstateObjectRepository realEstateObjectRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.realEstateObjectRepository = realEstateObjectRepository;
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }

    public boolean createTransaction(Transaction transaction) {
        User customer = userRepository.getById(Long.parseLong(transaction.getCustomerTextId()));
        transaction.setCustomer(customer);
        User developer = userRepository.getById(Long.parseLong(transaction.getDeveloperTextId()));
        transaction.setDeveloper(developer);
        RealEstateObject object = realEstateObjectRepository.getById(Long.parseLong(transaction.getObjectTextId()));
        transaction.setObject(object);
        transactionRepository.save(transaction);
        return true;
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
