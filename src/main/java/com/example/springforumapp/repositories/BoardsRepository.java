package com.example.springforumapp.repositories;

import com.example.springforumapp.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardsRepository extends JpaRepository<Board, Integer> {
    public Board findBoardByName(String name);
}
