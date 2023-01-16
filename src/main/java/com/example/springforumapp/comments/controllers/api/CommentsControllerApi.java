package com.example.springforumapp.comments.controllers.api;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.models.dto.CommentInputDTO;
import com.example.springforumapp.comments.services.CommentsService;
import com.example.springforumapp.comments.util.validators.CommentValidator;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.services.PublicationsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentsControllerApi {
    private final CommentsService commentsService;
    private final PublicationsService publicationsService;
    private final CommentValidator commentValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentsControllerApi(CommentsService commentsService, PublicationsService publicationsService, CommentValidator commentValidator, ModelMapper modelMapper) {
        this.commentsService = commentsService;
        this.publicationsService = publicationsService;
        this.commentValidator = commentValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/boards/{boardName}/{id}")
    public ResponseEntity<ResponseApi> addCommentToPublication(
            @PathVariable("boardName") String boardName,
            @PathVariable("id") int id,
            @RequestBody @Valid CommentInputDTO commentInputDTO,
            BindingResult bindingResult){
        commentValidator.validate(commentInputDTO, bindingResult);

        Publication publication = publicationsService.getPublication(id, boardName);
        Comment comment = modelMapper.map(commentInputDTO, Comment.class);
        commentsService.addComment(comment);

        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }
}
