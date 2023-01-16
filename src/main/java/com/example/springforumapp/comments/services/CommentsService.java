package com.example.springforumapp.comments.services;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Transactional
    public void addComment(Comment comment){
        comment.setDateOfCreation(LocalDate.now());
        commentsRepository.save(comment);
    }

    //return list without any reply ;
    public List<Comment> getCommentsForShowingInPublication(int publicationId){
        List<Comment> commentsList = commentsRepository.findAllByPublicationId(publicationId);
        List<Comment> commentsListEdited = new ArrayList<Comment>();
        for(Comment comment:commentsList){
            if (comment.getParentComment() == null){
                commentsListEdited.add(comment);
            }
        }
        return commentsListEdited;
    }

}
