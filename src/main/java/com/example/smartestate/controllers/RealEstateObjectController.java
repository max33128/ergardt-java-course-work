package com.example.smartestate.controllers;

import com.example.smartestate.models.RealEstateObject;
import com.example.smartestate.models.User;
import com.example.smartestate.repositories.UserRepository;
import com.example.smartestate.services.RealEstateObjectService;
import com.example.smartestate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class RealEstateObjectController {

    private final RealEstateObjectService realEstateObjectService;
    private final UserService userService;
    private final UserRepository userRepository;

    public RealEstateObjectController(RealEstateObjectService realEstateObjectService, UserService userService, UserRepository userRepository) {
        this.realEstateObjectService = realEstateObjectService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/objects/add")
    public String createObject(RealEstateObject object, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        if (user.isAgent()) {
            object.setAgent(user);
            Long devId = Long.parseLong(object.getDeveloperText());
            object.setDeveloper(userRepository.getById(devId));
        } else if (user.isDeveloper()) {
            object.setDeveloper(user);
            Long agentId = Long.parseLong(object.getAgentText());
            object.setAgent(userRepository.getById(agentId));
        } else {
            Long devId = Long.parseLong(object.getDeveloperText());
            object.setDeveloper(userRepository.getById(devId));
            Long agentId = Long.parseLong(object.getAgentText());
            object.setAgent(userRepository.getById(agentId));
        }
        if (object.getDeveloper().isOnlyPrimal()) {
            object.setTypeOfHousing("Первичное жилье");
        } else {
            object.setTypeOfHousing("Вторичное жилье");
        }
        realEstateObjectService.createRealEstateObject(object);
        return "redirect:/real-estate-objects";
    }

    @PostMapping("/objects/edit")
    public String editObjects(@ModelAttribute RealEstateObject object, Model model) {
        realEstateObjectService.editRealEstateObject(object);
        return "redirect:/real-estate-objects";
    }

    @PostMapping("/objects/delete/{id}")
    public String deleteObject(@PathVariable Long id) {
        realEstateObjectService.deleteById(id);
        return "redirect:/real-estate-objects";
    }
}
