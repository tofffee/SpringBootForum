package com.example.springforumapp.testfeatures;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> smth(){
        Greeting greeting = new Greeting();
        greeting.setId(1L);
        greeting.setName("qwe");
        GreetingDTO dto = modelMapper.map(greeting, GreetingDTO.class);
        return ResponseEntity.ok(dto)  ;
    }

    @PostMapping
    public String smth(Greeting greeting){
        greetingService.saveGreeting(greeting);
        return "testPage";
    }
}
