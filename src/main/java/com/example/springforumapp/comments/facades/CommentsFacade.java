package com.example.springforumapp.comments.facades;

import com.example.springforumapp.comments.models.dto.CommentInDTO;
import com.example.springforumapp.comments.models.dto.CommentOutDTO;
import com.example.springforumapp.security.UserDetailsImpl;

import java.util.List;

public interface CommentsFacade {
    List<CommentOutDTO> findCommentsByPublicationId(String boardName, long publicationId);
    public CommentOutDTO createComment(UserDetailsImpl userDetails, String boardName, long publicationId, CommentInDTO commentInDTO);
}
