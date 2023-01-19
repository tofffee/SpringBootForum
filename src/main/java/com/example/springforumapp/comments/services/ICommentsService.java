package com.example.springforumapp.comments.services;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;

import java.time.LocalDate;

public interface ICommentsService {
    public void addComment(Comment comment, Publication publication, User user);
}
