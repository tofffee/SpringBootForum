package com.example.springforumapp.boards.controllers.api;

import com.example.springforumapp.boards.models.dto.BoardOutDTO;
import com.example.springforumapp.boards.services.BoardsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BoardsControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardsServiceImpl boardsService;

    @Test
    public void getAllBoardsApiTest() throws Exception {
        List<BoardOutDTO> boards = Arrays.asList(new BoardOutDTO(), new BoardOutDTO());
        when(boardsService.findAllBoards()).thenReturn(boards);

        mockMvc.perform(get("/api/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.body", hasSize(2)));
    }

    @Test
    void createBoardApi() {
    }

    @Test
    void changeBoardApi() {
    }

    @Test
    void deleteBoardApi() {
    }

    @Test
    void deleteAllBoardsApi() {
    }
}