package com.example.springforumapp.home.controllers.api;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardDTO;
import com.example.springforumapp.boards.services.BoardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeControllerApi {

    private final BoardsService boardsService;

    @Autowired
    public HomeControllerApi(BoardsService boardsService) {
        this.boardsService = boardsService;
    }

    @GetMapping()
    public ResponseEntity<?> getHomeApi(){
        List<Board> boardList = boardsService.getAllBoards();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardList.stream().forEach(board -> {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setId(board.getId());
            boardDTO.setName(board.getName());
            boardDTOList.add(boardDTO);
        } );
        return ResponseEntity.ok(boardDTOList);
    }
}
