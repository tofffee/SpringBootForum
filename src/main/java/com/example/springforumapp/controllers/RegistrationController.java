package com.example.springforumapp.controllers;

import com.example.springforumapp.models.User;
import com.example.springforumapp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("")
    public String getRegistrationPage(@ModelAttribute("user") User user){
        return "registration/registrationPage";
    }

    @PostMapping("")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){

        //userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            return "auth/registration";
        }

        registrationService.registerUser(user);

        return "redirect:/auth/login";
    }
}
