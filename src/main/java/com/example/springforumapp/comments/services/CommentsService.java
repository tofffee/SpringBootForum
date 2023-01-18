package com.example.springforumapp.comments.services;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.repositories.CommentsRepository;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    @Transactional
    public void addComment(Comment comment, Publication publication, User user){
        comment.setPublication(publication);
        comment.setUser(user);
        comment.setDateOfCreation(LocalDate.now());
        commentsRepository.save(comment);
    }

}
