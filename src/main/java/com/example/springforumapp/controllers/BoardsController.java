package com.example.springforumapp.controllers;

import com.example.springforumapp.models.Board;
import com.example.springforumapp.models.Comment;
import com.example.springforumapp.models.Publication;
import com.example.springforumapp.services.BoardsService;
import com.example.springforumapp.services.CommentsService;
import com.example.springforumapp.services.PublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class BoardsController {

    private final BoardsService boardsService;
    private final PublicationsService publicationsService;
    private final CommentsService commentsService;

    @Autowired
    public BoardsController(BoardsService boardsService, PublicationsService publicationsService, CommentsService commentsService) {
        this.boardsService = boardsService;
        this.publicationsService = publicationsService;
        this.commentsService = commentsService;
    }


    //Получить страницы борды
    @GetMapping("/{boardName}")
    public String getBoardPage(@PathVariable("boardName") String boardName, Model model){
        Board board = boardsService.findBoardByName(boardName);
        model.addAttribute("board",board);
        model.addAttribute("publication",new Publication());
        return "/boards/boardPage";
    }

    //Создать публикацию в борде
    @PostMapping("/{boardName}")
    public String addPublication(
            @PathVariable("boardName") String boardName,
            Model model,
            @ModelAttribute("publication") @Valid Publication publication,
            BindingResult bindingResult
            ){
        Board board = boardsService.findBoardByName(boardName);
        model.addAttribute("board",board);
        if(bindingResult.hasErrors()){
            return "/boards/boardPage";
        }else{
            publication.setBoard(board);
            publicationsService.savePublication(publication);
            return "redirect:/boards/"+boardName +"/"+publication.getId(); // перенаправление на страницу с новой публикацией в борде
        }

    }

    //получить страницу публикации в борде
    @GetMapping("/{boardName}/{publicationId}")
    public String getPublicationPage(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") int publicationId,
            @ModelAttribute("comment") Comment comment,
            Model model
    ){
        Board board = boardsService.findBoardByName(boardName);
        Optional<Publication> publication = publicationsService.findPublicationById(publicationId);
        model.addAttribute("board",board);
        model.addAttribute("publication",publication.get());

        return "/boards/publications/publicationPage";
    }


    // создать комментарий в публикации в борде
    @PostMapping("/{boardName}/{publicationId}/create")
    public String addComment(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") int publicationId,
            Model model,
            @ModelAttribute("comment") @Valid Comment comment,
            BindingResult bindingResult
    ){
        Board board = boardsService.findBoardByName(boardName);
        Optional<Publication> publication = publicationsService.findPublicationById(publicationId);
        model.addAttribute("board",board);
        model.addAttribute("publication",publication.get());
        if(bindingResult.hasErrors()){
            return "/boards/publications/publicationPage";
        }else{
            comment.setPublication(publication.get());
            commentsService.saveComment(comment);
            return "redirect:/boards/"+boardName +"/"+publicationId ; // обновление страницы с комментариями
        }

    }

}
