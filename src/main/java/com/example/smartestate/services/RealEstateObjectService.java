package com.example.smartestate.services;

import com.example.smartestate.models.RealEstateObject;
import com.example.smartestate.repositories.RealEstateObjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEstateObjectService {

    private final RealEstateObjectRepository realEstateObjectRepository;

    public RealEstateObjectService(RealEstateObjectRepository realEstateObjectRepository) {
        this.realEstateObjectRepository = realEstateObjectRepository;
    }

    public List<RealEstateObject> list() {
        return realEstateObjectRepository.findAll();
    }

    public boolean createRealEstateObject(RealEstateObject object) {
        object.setStateOfObject("Продается");
        realEstateObjectRepository.save(object);
        return true;
    }

    public void editRealEstateObject(RealEstateObject object) {
        RealEstateObject oldObject = realEstateObjectRepository.findById(object.getId()).get();
        oldObject.setAddress(object.getAddress());
        oldObject.setSquare(object.getSquare());
        oldObject.setPrice(object.getPrice());
        oldObject.setFloor(object.getFloor());
        oldObject.setNumberOfRooms(object.getNumberOfRooms());
        oldObject.setTypeOfHousing(object.getTypeOfHousing());
        oldObject.setStateOfObject(object.getStateOfObject());
        oldObject.setDescription(object.getDescription());
        realEstateObjectRepository.save(oldObject);
    }

    public void deleteById(Long id) {
        realEstateObjectRepository.deleteById(id);
    }
}
