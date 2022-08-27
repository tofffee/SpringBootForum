package com.example.springforumapp.services;

import com.example.springforumapp.models.Board;
import com.example.springforumapp.repositories.BoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;

    @Autowired
    public BoardsService(BoardsRepository boardsRepository) {
        this.boardsRepository = boardsRepository;
    }

    public List<Board> getAllBoards(){
        return boardsRepository.findAll();
    }

    public Board findBoardByName(String name){
        return boardsRepository.findBoardByName(name);
    }
}
