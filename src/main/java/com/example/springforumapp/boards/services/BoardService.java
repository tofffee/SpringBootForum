package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardInDTO;
import com.example.springforumapp.boards.models.dto.BoardOutDTO;

import java.util.List;

public interface BoardService {
    public List<BoardOutDTO> findAllBoards();

    public Board findByName(String name);

    public BoardOutDTO createBoard(BoardInDTO boardInDTO);

    public void deleteBoard(long id);

    public BoardOutDTO changeBoard(long id, BoardInDTO boardInDTO);

    public void deleteAllBoards();
}
