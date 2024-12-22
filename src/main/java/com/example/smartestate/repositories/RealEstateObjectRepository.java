package com.example.smartestate.repositories;

import com.example.smartestate.models.RealEstateObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateObjectRepository extends JpaRepository<RealEstateObject, Long> {
    RealEstateObject findByAddress(String address);
}
