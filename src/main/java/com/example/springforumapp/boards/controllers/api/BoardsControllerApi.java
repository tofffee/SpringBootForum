package com.example.springforumapp.boards.controllers.api;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardDTO;
import com.example.springforumapp.boards.services.BoardsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/boards")
public class BoardsControllerApi {

    private final BoardsService boardsService;
    private final ModelMapper modelMapper;

    @Autowired
    public BoardsControllerApi(BoardsService boardsService, ModelMapper modelMapper) {
        this.boardsService = boardsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllBoardsApi(){
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
