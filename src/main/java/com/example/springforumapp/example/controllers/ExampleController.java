package com.example.springforumapp.example.controllers;


import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ExampleController {

    private final UsersService usersService;

    @Autowired
    public ExampleController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name",defaultValue = "empty") String name){
        return "Hello " + name;
    }

}




















