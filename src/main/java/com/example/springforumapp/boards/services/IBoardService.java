package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardInputDTO;
import com.example.springforumapp.boards.util.exceptions.BoardException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IBoardService {
    public List<Board> getAllBoards();

    public Board findByName(String name);

    public void addBoard(Board board);

    public void deleteBoard(long id);

    public void changeBoard(long id, BoardInputDTO boardInputDTO);

    public void deleteAllBoards();
}
