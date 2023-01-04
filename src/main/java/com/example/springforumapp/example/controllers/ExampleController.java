package com.example.springforumapp.example.controllers;


import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class ExampleController {


//    @GetMapping("/test")
//    @ResponseBody
//    public Map<String,Object> testOuath(OAuth2AuthentificationToken oAuth2AuthentificationToken)

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestParam(name = "name",defaultValue = "empty") String name){
        return "Hello " + name;
    }

    @GetMapping("/qweqeqwet")
    @ResponseBody
    public String sayQwe(){
        return "hello world";
    }
}




















