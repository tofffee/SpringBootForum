package com.example.springforumapp.boards.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.models.dto.BoardInDTO;
import com.example.springforumapp.boards.models.dto.BoardOutDTO;
import com.example.springforumapp.boards.repositories.BoardsRepository;
import com.example.springforumapp.boards.util.exceptions.BoardNotFoundException;
import com.example.springforumapp.boards.util.exceptions.BoardCreateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardsServiceImpl implements BoardService {
    private final BoardsRepository boardsRepository;

    private final ModelMapper modelMapper;

    @Override
    public Board findByName(String name) throws BoardNotFoundException{
        Optional<Board> board = boardsRepository.findByName(name);
        if(board.isEmpty())
            throw new BoardNotFoundException("Such board is not found",
                    "BoardService.java: BoardNotFoundException");

        return board.get();
    }
    @Override
    public List<BoardOutDTO> findAllBoards(){
        List<Board> boards = boardsRepository.findAll();
        return boardsToOutputDTOs(boards);
    }

    @Override
    @Transactional
    public BoardOutDTO createBoard(BoardInDTO boardInDTO) throws BoardCreateException {
        Board board = modelMapper.map(boardInDTO, Board.class);
        if(boardsRepository.findByName(board.getName()).isPresent())
            throw new BoardCreateException("Board with such name is already exists",
                                            "BoardService.java: BoardCreateException");
        boardsRepository.save(board);
        return boardToOutputDTO(board);
    }

    @Override
    @Transactional
    public BoardOutDTO changeBoard(long id, BoardInDTO boardInDTO) throws BoardNotFoundException {
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isEmpty())
            throw new BoardNotFoundException("Such board does not exist","BoardService.java: BoardNotFoundException");

        board.get().setName(boardInDTO.getName());
        boardsRepository.save(board.get());
        return boardToOutputDTO(board.get());
    }

    @Override
    @Transactional
    public void deleteBoard(long id) throws BoardNotFoundException {
        Optional<Board> board = boardsRepository.findById(id);
        if (board.isEmpty())
            throw new BoardNotFoundException("Such board does not exist",
                                             "BoardService.java: BoardNotFoundException");

        boardsRepository.delete(board.get());
    }



    @Override
    @Transactional
    public void deleteAllBoards(){
        boardsRepository.deleteAll();
    }




    private BoardOutDTO boardToOutputDTO(Board board){
        return modelMapper.map(board, BoardOutDTO.class);
    }

    private List<BoardOutDTO> boardsToOutputDTOs(List<Board> boards){

        List<BoardOutDTO> boardOutDTOList = new ArrayList<>();
        boards.forEach(board -> {
            boardOutDTOList.add(boardToOutputDTO(board));
        });
        return boardOutDTOList;
    }

}
