package com.example.springforumapp.auth.controllers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/auth/login")
    public String getLoginPage(){
        return "loginPage";
    }

    @GetMapping("/")
    public String getHomePage(){
        return "homePage";
    }


    @GetMapping("/forauthorized")
    public String getForAuthorizedPage(){
        return "forAuthorizedPage";
    }
}