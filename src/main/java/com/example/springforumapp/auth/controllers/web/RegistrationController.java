//package com.example.springforumapp.auth.controllers.web;
//
//import com.example.springforumapp.users.models.domain.User;
//import com.example.springforumapp.auth.services.RegistrationService;
//import com.example.springforumapp.users.services.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/registration")
//public class RegistrationController {
//
//    private final RegistrationService registrationService;
//    private final UsersService userDetailsService;
//
//    @Autowired
//    public RegistrationController(RegistrationService registrationService, UsersService userDetailsService) {
//        this.registrationService = registrationService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @GetMapping()
//    public String getRegistrationPage(@ModelAttribute("user") User user){
//        return "registration/registrationPage";
//    }
//
//    @PostMapping()
//    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
//
//        //userValidator.validate(user, bindingResult);
//        if(bindingResult.hasErrors()){
//            return "registration/registrationPage";
//        }
//
//        registrationService.registerUser(user);
//
//        return "redirect:/auth/login";
//    }
//
//    @GetMapping("/activate/{code}")
//    public String activateUser(Model model, @PathVariable(name = "code") String code)
//    {
//        boolean isUserActivated = userDetailsService.activateUser(code);
//        if ( isUserActivated == true ){
//            return "/registration/UserActivationSuccessPage";
//        }else{
//            return "/registration/UserActivationErrorPage";
//        }
//    }
//}
