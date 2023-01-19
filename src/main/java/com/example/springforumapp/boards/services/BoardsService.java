package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.repositories.BoardsRepository;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardsService implements IBoardService {
    private final BoardsRepository boardsRepository;

    public List<Board> getAllBoards(){
        return boardsRepository.findAll();
    }

    public Board findByName(String name) throws BoardException{
        Optional<Board> board = boardsRepository.findByName(name);
        if(board.isEmpty())
            throw new BoardException("Such board is not found","BoardService.java: BoardException");

        return board.get();
    }

    @Transactional
    public void addBoard(Board board) throws BoardException{
        if(boardsRepository.findByName(board.getName()).isPresent())
            throw new BoardException("Board with such name is already exists","BoardService.java: BoardException");

        boardsRepository.save(board);
    }

    @Transactional
    public void deleteBoard(long id) throws BoardException{
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isEmpty())
            throw new BoardException("Such board does not is","BoardService.java: BoardException");

        boardsRepository.delete(board.get());
    }

    @Transactional
    public void changeBoard(long id, BoardInputDTO boardInputDTO) throws BoardException{
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isEmpty())
            throw new BoardException("Such board does not is","BoardService.java: BoardException");

        board.get().setName(boardInputDTO.getName());
        boardsRepository.save(board.get());
    }

    @Transactional
    public void deleteAllBoards(){
        boardsRepository.deleteAll();
    }
}
