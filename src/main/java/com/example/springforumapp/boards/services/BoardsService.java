package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardDTO;
import com.example.springforumapp.boards.models.dto.BoardDTOCreateDeletePut;
import com.example.springforumapp.boards.repositories.BoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardsService {
    private final BoardsRepository boardsRepository;

    @Autowired
    public BoardsService(BoardsRepository boardsRepository) {
        this.boardsRepository = boardsRepository;
    }

    public List<Board> getAllBoards(){
        return boardsRepository.findAll();
    }

    public Board findBoardById(int id){
        return boardsRepository.findBoardById(id);
    }
    public Board findBoardByName(String name){
        return boardsRepository.findBoardByName(name);
    }

    public void addBoard(BoardDTOCreateDeletePut boardDTOCreateDeletePut){
        Board board = new Board();
        board.setName(boardDTOCreateDeletePut.getName());
        boardsRepository.save(board);
    }

    public void deleteBoard(int id){
        Board board = boardsRepository.findBoardById(id);
        boardsRepository.delete(board);
    }

    public void changeBoard(int id, BoardDTOCreateDeletePut boardDTOCreateDeletePut){
        Board board = boardsRepository.findBoardById(id);
        board.setName(boardDTOCreateDeletePut.getName());
        boardsRepository.save(board);
    }

    public void deleteAllBoards(){
        boardsRepository.deleteAll();
    }
}
