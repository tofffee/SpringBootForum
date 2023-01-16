package com.example.springforumapp.publications.repositories;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.publications.models.domain.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationsRepository extends JpaRepository<Publication,Integer> {
    Page<Publication> findAllByBoardName(String boardName, Pageable pageable);
    Optional<Publication> findByIdAndBoardName(int id, String boardName);
}
