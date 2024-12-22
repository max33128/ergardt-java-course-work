package com.example.smartestate.services;

import com.example.smartestate.models.Application;
import com.example.smartestate.models.RealEstateObject;
import com.example.smartestate.models.User;
import com.example.smartestate.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> list() {
        return applicationRepository.findAll();
    }

    public boolean createApplication(Application application, User user) {
        application.setCustomer(user);
        applicationRepository.save(application);
        return true;
    }

    public void editApplication(Application application) {
        Application oldApplication = applicationRepository.findById(application.getId()).get();
        oldApplication.setSquare(application.getSquare());
        oldApplication.setPrice(application.getPrice());
        oldApplication.setFloor(application.getFloor());
        oldApplication.setNumberOfRooms(application.getNumberOfRooms());
        oldApplication.setTypeOfHousing(application.getTypeOfHousing());
        oldApplication.setDescription(application.getDescription());
        applicationRepository.save(oldApplication);
    }

    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

}
