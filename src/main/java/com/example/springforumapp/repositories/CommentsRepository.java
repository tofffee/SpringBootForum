package com.example.springforumapp.repositories;

import com.example.springforumapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {

}
