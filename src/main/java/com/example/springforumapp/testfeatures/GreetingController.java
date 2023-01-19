package com.example.springforumapp.testfeatures;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;


    @GetMapping
    public String smth(Model model){
        model.addAttribute("greeting", new Greeting());
        return "testPage";
    }

    @PostMapping
    public String smth(Greeting greeting){
        greetingService.saveGreeting(greeting);
        return "testPage";
    }
}
