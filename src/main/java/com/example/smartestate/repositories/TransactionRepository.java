package com.example.smartestate.repositories;

import com.example.smartestate.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findBySummary(Double summary);
}
