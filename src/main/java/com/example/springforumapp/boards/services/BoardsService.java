package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
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

    public Board findBoardByName(String name){
        return boardsRepository.findBoardByName(name);
    }
}
