package com.example.springforumapp.comments.services;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.models.dto.CommentInDTO;
import com.example.springforumapp.comments.models.dto.CommentOutDTO;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;

import java.util.List;

public interface CommentsService {
    public Comment findCommentById(Long id);
    public List<CommentOutDTO> findCommentsByPublication(Publication publication);
    public CommentOutDTO createComment(User user, Publication publication, CommentInDTO commentInDTO);
}
