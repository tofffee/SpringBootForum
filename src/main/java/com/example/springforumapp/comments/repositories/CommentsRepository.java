package com.example.springforumapp.comments.repositories;

import com.example.springforumapp.comments.models.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {

}
