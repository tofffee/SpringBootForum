package com.example.springforumapp.controllers;

import com.example.springforumapp.dto.CommentDTO;
import com.example.springforumapp.models.Board;
import com.example.springforumapp.models.Comment;
import com.example.springforumapp.models.Publication;
import com.example.springforumapp.services.BoardsService;
import com.example.springforumapp.services.CommentsService;
import com.example.springforumapp.services.PublicationsService;
import com.example.springforumapp.util.comments.CommentNotSavedException;
import com.example.springforumapp.util.comments.CommentResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/boards/{boardName}/{publicationId}")
public class PublicationsController {

    private final BoardsService boardsService;
    private final PublicationsService publicationsService;
    private final CommentsService commentsService;

    @Autowired
    public PublicationsController(BoardsService boardsService, PublicationsService publicationsService, CommentsService commentsService)
    {
        this.boardsService = boardsService;
        this.publicationsService = publicationsService;
        this.commentsService = commentsService;
    }

    //получить страницу публикации в борде
    @GetMapping()
    public String getPublicationPage(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") int publicationId,
            Model model
    ){

        Board board = boardsService.findBoardByName(boardName);
        Optional<Publication> publication = publicationsService.findPublicationById(publicationId);
        Comment comment = new Comment();

        model.addAttribute("board",board);
        model.addAttribute("publication",publication.get());
        model.addAttribute("comment", comment);

        return "/boards/publications/publicationPage";
    }

    // создать комментарий в публикации в борде
    @ResponseBody
    @PostMapping()
    public ResponseEntity<HttpStatus> addComment(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") int publicationId,
            @RequestBody @Valid CommentDTO commentDTO,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage
                        .append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new CommentNotSavedException(errorMessage.toString());
        }

        Board board = boardsService.findBoardByName(boardName);
        Optional<Publication> publication = publicationsService.findPublicationById(publicationId);

        Comment comment = new Comment();
        comment.setTextOfComment(commentDTO.getTextOfComment());
        comment.setPublication(publication.get());
        commentsService.saveComment(comment);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<CommentResponseError> handleException(CommentNotSavedException commentNotSavedException)
    {
        CommentResponseError commentResponseError = new CommentResponseError(
                commentNotSavedException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(commentResponseError, HttpStatus.BAD_REQUEST);
    }


}


