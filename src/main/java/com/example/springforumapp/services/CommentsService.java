package com.example.springforumapp.services;

import com.example.springforumapp.models.Comment;
import com.example.springforumapp.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }


    public void saveComment(Comment comment){
        comment.setTimeOfComment(LocalDate.now());
        commentsRepository.save(comment);
    }

}
