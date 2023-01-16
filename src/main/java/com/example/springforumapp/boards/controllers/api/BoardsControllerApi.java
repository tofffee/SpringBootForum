package com.example.springforumapp.boards.controllers.api;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardOutputDTO;
import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.boards.util.validators.BoardValidator;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardsControllerApi {

    private final BoardsService boardsService;
    private final BoardValidator boardValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public BoardsControllerApi(BoardsService boardsService, BoardValidator boardValidator, ModelMapper modelMapper) {
        this.boardsService = boardsService;
        this.boardValidator = boardValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<ResponseApi> getAllBoardsApi(){
        List<Board> boards = boardsService.getAllBoards();
        List<BoardOutputDTO> dtos = boardsToOutputDTOs(boards);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dtos));
    }

    @PostMapping()
    public ResponseEntity<ResponseApi> addBoardApi(@RequestBody @Valid BoardInputDTO boardInputDTO, BindingResult bindingResult){
        boardValidator.validate(boardInputDTO, bindingResult);

        Board board = modelMapper.map(boardInputDTO, Board.class);
        boardsService.addBoard(board);
        BoardOutputDTO dto = boardToOutputDTO(board);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteBoardApi(@PathVariable int id){
        boardsService.deleteBoard(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Board deleted successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> changeBoardApi(@PathVariable int id, @RequestBody BoardInputDTO boardInputDTO){
        boardsService.changeBoard(id, boardInputDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Board updated successfully"));
    }

    @DeleteMapping()
    public ResponseEntity<ResponseApi> deleteAllBoardsApi(){
        boardsService.deleteAllBoards();
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "All boards deleted successfully"));
    }

    private BoardOutputDTO boardToOutputDTO(Board board){
        return modelMapper.map(board, BoardOutputDTO.class);
    }

    private List<BoardOutputDTO> boardsToOutputDTOs(List<Board> boards){

        List<BoardOutputDTO> boardOutputDTOList = new ArrayList<>();
        boards.forEach(board -> {
            boardOutputDTOList.add(boardToOutputDTO(board));
        });
        return boardOutputDTOList;
    }

}
