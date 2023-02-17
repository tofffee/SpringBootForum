package com.example.springforumapp;


import com.example.springforumapp.files.services.StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class SpringForumAppApplication implements CommandLineRunner {
    private final StorageServiceImpl storageService;
    public static void main(String[] args) {
        SpringApplication.run(SpringForumAppApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }
}
