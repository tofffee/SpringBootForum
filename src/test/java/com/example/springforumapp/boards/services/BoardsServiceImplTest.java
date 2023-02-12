package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardInDTO;
import com.example.springforumapp.boards.models.dto.BoardOutDTO;
import com.example.springforumapp.boards.repositories.BoardsRepository;
import com.example.springforumapp.boards.util.exceptions.BoardCreateException;
import com.example.springforumapp.boards.util.exceptions.BoardNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BoardsServiceImplTest {

    @Autowired
    private BoardsServiceImpl boardsService;
    @MockBean
    private BoardsRepository boardsRepository;

    @Test
    void test_findByName_board_found_success() {
        Board board = Board.builder().id(2).name("Sport").build();
        when(boardsRepository.findByName("Sport")).thenReturn(Optional.of(board));

        Board returnedBoard = boardsService.findByName("Sport");
        assertEquals(board, returnedBoard);
    }

    @Test
    void test_findByName_board_not_found_error() {
        when(boardsRepository.findByName("NotExistedNameBoard")).thenReturn(Optional.empty());

        Exception exception = assertThrows(BoardNotFoundException.class, () -> boardsService.findByName("NotExistedNameBoard"));
        assertEquals("Such board is not found", exception.getMessage());
    }

    @Test
    void test_findAllBoards_success() {
        List<Board> boards = Arrays.asList(Board.builder().id(1).name("Sport").build(),
                                           Board.builder().id(2).name("Games").build());
        when(boardsRepository.findAll()).thenReturn(boards);

        List<BoardOutDTO> actualBoards = boardsService.findAllBoards();

        assertEquals(boards.size(), actualBoards.size());
        for (int i = 0; i < boards.size(); i++) {
            assertEquals(boards.get(i).getId(), actualBoards.get(i).getId());
            assertEquals(boards.get(i).getName(), actualBoards.get(i).getName());
        }
    }

    @Test
    void test_createBoard_success() {
        BoardInDTO boardInDTO = BoardInDTO.builder().name("Sport").build();
        Board board = new ModelMapper().map(boardInDTO, Board.class);

        when(boardsRepository.findByName("Sport")).thenReturn(Optional.empty());
        when(boardsRepository.save(board)).thenReturn(board);

        BoardOutDTO boardOutDTO = boardsService.createBoard(boardInDTO);
        assertEquals("Sport", boardOutDTO.getName());
    }

    @Test
    void test_createBoard_boardWithSucnNameAlreadyExist_error() {
        BoardInDTO boardInDTO = BoardInDTO.builder().name("Sport").build();
        Board board = new ModelMapper().map(boardInDTO, Board.class);

        when(boardsRepository.findByName("Sport")).thenReturn(Optional.of(board));
        assertThrows(BoardCreateException.class, () -> {
            boardsService.createBoard(boardInDTO);
        });
    }

    @Test
    void changeBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void deleteAllBoards() {
    }
}