package com.example.smartestate.repositories;

import com.example.smartestate.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findByPrice(Double price);
}
