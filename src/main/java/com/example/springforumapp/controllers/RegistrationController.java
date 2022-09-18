package com.example.springforumapp.controllers;

import com.example.springforumapp.models.User;
import com.example.springforumapp.services.RegistrationService;
import com.example.springforumapp.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserDetailsServiceImpl userDetailsService) {
        this.registrationService = registrationService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("")
    public String getRegistrationPage(@ModelAttribute("user") User user){
        return "registration/registrationPage";
    }

    @PostMapping("")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){

        //userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration/registrationPage";
        }

        registrationService.registerUser(user);

        return "redirect:/auth/login";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(Model model, @PathVariable(name = "code") String code)
    {
        boolean isUserActivated = userDetailsService.activateUser(code);
        if ( isUserActivated == true ){
            return "/registration/UserActivationSuccessPage";
        }else{
            return "/registration/UserActivationErrorPage";
        }
    }
}
