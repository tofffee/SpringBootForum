package com.example.springforumapp.boards.controllers.api;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardOutputDTO;
import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
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
    public ResponseEntity<ResponseApi> getAllBoardsApi(){
        List<Board> boards = boardsService.getAllBoards();
        List<BoardOutputDTO> boardOutputDTOS = boardsToDTO(boards);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), boardOutputDTOS));
    }

    @PostMapping()
    public ResponseEntity<?> addBoard(@RequestBody BoardInputDTO boardInputDTO){
        boardsService.addBoard(boardInputDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Board created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable int id){
        boardsService.deleteBoard(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Board deleted successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeBoard(@PathVariable int id, @RequestBody BoardInputDTO boardInputDTO){
        boardsService.changeBoard(id, boardInputDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Board updated successfully"));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllBoards(){
        boardsService.deleteAllBoards();
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "All boards deleted successfully"));
    }

    private List<BoardOutputDTO> boardsToDTO(List<Board> boards){
        List<BoardOutputDTO> boardOutputDTOList = new ArrayList<>();
        boards.forEach(board -> {
            BoardOutputDTO boardOutputDTO = new BoardOutputDTO();
            boardOutputDTO.setId(board.getId());
            boardOutputDTO.setName(board.getName());
            boardOutputDTOList.add(boardOutputDTO);
        } );
        return boardOutputDTOList;
    }

}
