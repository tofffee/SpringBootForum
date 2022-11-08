package com.example.springforumapp.comments.services;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Comment findCommentById(int id)
    {
        Optional<Comment> comment = commentsRepository.findById(id);
        if (comment.isPresent())
        {
            return comment.get();
        }
        else
        {
            return null;
        }
    }
    public void saveComment(Comment comment){
        comment.setTimeOfComment(LocalDate.now());
        commentsRepository.save(comment);
    }

}
