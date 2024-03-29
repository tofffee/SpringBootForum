package com.example.springforumapp.boards.repositories;

import com.example.springforumapp.boards.models.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardsRepository extends JpaRepository<Board, Long> {
    public Optional<Board> findByName(String name);
}
