package com.example.springforumapp.boards.controllers.api;

import com.example.springforumapp.boards.models.dto.BoardOutDTO;
import com.example.springforumapp.boards.models.dto.BoardInDTO;
import com.example.springforumapp.boards.services.BoardsServiceImpl;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@Slf4j
@RequiredArgsConstructor
public class BoardsControllerApi {
    private final BoardsServiceImpl boardsService;

    @GetMapping()
    public ResponseEntity<ResponseApi> getAllBoardsApi(){
        List<BoardOutDTO> dtos = boardsService.findAllBoards();
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dtos));
    }

    @PostMapping()
    public ResponseEntity<ResponseApi> createBoardApi(@RequestBody @Valid BoardInDTO boardInDTO){
        BoardOutDTO dto = boardsService.createBoard(boardInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> changeBoardApi(@PathVariable long id, @RequestBody @Valid BoardInDTO boardInDTO){
        BoardOutDTO dto = boardsService.changeBoard(id, boardInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteBoardApi(@PathVariable long id){
        boardsService.deleteBoard(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "Board deleted successfully"));
    }

    @DeleteMapping()
    public ResponseEntity<ResponseApi> deleteAllBoardsApi(){
        boardsService.deleteAllBoards();
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "All boards deleted successfully"));
    }


}
