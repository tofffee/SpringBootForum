package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.repositories.BoardsRepository;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isPresent())
            return board.get();
        else throw new BoardException("Such board is not found","BoardService.java: BoardException");
    }
    public Board findBoardByName(String name){
        Optional<Board> board = boardsRepository.findBoardByName(name);
        if (board.isPresent())
            return board.get();
        else throw new BoardException("Such board is not found","BoardService.java: BoardException");
    }

    public void addBoard(BoardInputDTO boardInputDTO){
        Board board = new Board();
        board.setName(boardInputDTO.getName());
        boardsRepository.save(board);
    }

    public void deleteBoard(int id){
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isPresent())
            boardsRepository.delete(board.get());
        else throw new BoardException("Board can't be deleted","BoardService.java: BoardException");
    }

    public void changeBoard(int id, BoardInputDTO boardInputDTO){
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isPresent()){
            board.get().setName(boardInputDTO.getName());
            boardsRepository.save(board.get());
        }
        else throw new BoardException("Board can't be changed","BoardService.java: BoardException");
    }

    public void deleteAllBoards(){
        boardsRepository.deleteAll();
    }
}
