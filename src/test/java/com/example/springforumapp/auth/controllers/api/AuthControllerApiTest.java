package com.example.springforumapp.auth.controllers.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerApiTest {

    private final MockMvc mockMvc;

    AuthControllerApiTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void loginApi() {
    }

    @Test
    void authUserApi() {
    }
}