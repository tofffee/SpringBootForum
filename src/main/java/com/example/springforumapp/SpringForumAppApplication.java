package com.example.springforumapp;


import com.example.springforumapp.files.services.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringForumAppApplication implements CommandLineRunner {

    private final StorageServiceImpl storageService;
    @Autowired
    public SpringForumAppApplication(StorageServiceImpl storageService) {
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
