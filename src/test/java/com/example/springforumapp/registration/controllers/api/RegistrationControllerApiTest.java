package com.example.springforumapp.registration.controllers.api;

import com.example.springforumapp.users.models.dto.RegisterInDTO;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerApiTest {
    private final MockMvc mockMvc;
    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;

//    @AfterEach
//    public void resetDb() {
//        usersRepository.deleteAll();
//    }

    @Autowired
    RegistrationControllerApiTest(MockMvc mockMvc, UsersRepository usersRepository, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.usersRepository = usersRepository;
        this.objectMapper = objectMapper;
    }

    @Test
    void registerApi_correct_data() throws Exception {
        RegisterInDTO registerInDTO = new RegisterInDTO("vasyaacc","vasya@mail.ru","12345");
        mockMvc.perform(post("/api/register")
                .content(objectMapper.writeValueAsString(registerInDTO))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.code").value(200));
    }

}