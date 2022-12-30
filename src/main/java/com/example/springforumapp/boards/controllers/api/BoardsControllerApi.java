package com.example.springforumapp.boards.controllers.api;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardDTO;
import com.example.springforumapp.boards.models.dto.BoardDTOCreateDeletePut;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.errors.ApiSuccess;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(),boardDTOList));
    }

    @PostMapping()
    public ResponseEntity<?> addBoard(@RequestBody BoardDTOCreateDeletePut boardDTOCreateDeletePut){
        boardsService.addBoard(boardDTOCreateDeletePut);
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(), "Board created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable int id){
        boardsService.deleteBoard(id);
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(), "Board deleted successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeBoard(@PathVariable int id, @RequestBody BoardDTOCreateDeletePut boardDTOCreateDeletePut){
        boardsService.changeBoard(id, boardDTOCreateDeletePut);
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(), "Board updated successfully"));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllBoards(){
            boardsService.deleteAllBoards();
        return ResponseEntity.ok(new ApiSuccess(HttpStatus.OK.value(), "All boards deleted successfully"));
    }

}
