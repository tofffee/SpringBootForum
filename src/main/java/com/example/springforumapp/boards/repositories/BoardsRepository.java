package com.example.springforumapp.boards.repositories;

import com.example.springforumapp.boards.models.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardsRepository extends JpaRepository<Board, Integer> {
    public Board findBoardById(int id);
    public Board findBoardByName(String name);
}
