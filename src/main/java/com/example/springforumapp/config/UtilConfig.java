package com.example.springforumapp.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@Slf4j
public class UtilConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    Random random() {
        return new Random();
    }
}
