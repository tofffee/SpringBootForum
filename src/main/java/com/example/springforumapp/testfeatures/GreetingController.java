package com.example.springforumapp.testfeatures;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> smth(){
        Greeting greeting = new Greeting();
        greeting.setId(1L);
        greeting.setName("qwe");
        GreetingRequestDTO dto = modelMapper.map(greeting, GreetingRequestDTO.class);
        return ResponseEntity.ok(dto)  ;
    }

    @PostMapping()
    public ResponseEntity<?> qwe(@RequestBody GreetingRequestDTO greetingRequestDTO){
        GreetingResponseDTO greetingResponseDTO = greetingService.saveGreeting(greetingRequestDTO);
        return ResponseEntity.ok(greetingResponseDTO);
    }
}
