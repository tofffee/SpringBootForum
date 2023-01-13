package com.example.springforumapp;


import com.example.springforumapp.files.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringForumAppApplication implements CommandLineRunner {

    private final StorageService storageService;
    @Autowired
    public SpringForumAppApplication(StorageService storageService) {
        this.storageService = storageService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringForumAppApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }
}
