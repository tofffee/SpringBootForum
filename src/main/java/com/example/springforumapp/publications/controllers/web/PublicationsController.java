package com.example.springforumapp.publications.controllers.web;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.comments.services.CommentsService;
import com.example.springforumapp.publications.services.PublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/boards/{boardName}")
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


    //Создать публикацию в борде
    @PostMapping()
    public String createPublicationInBoard(@PathVariable("boardName") String boardName,
                                           Model model,
                                           @Valid Publication publication,
                                           BindingResult bindingResult)
    {

        if(bindingResult.hasErrors()){
            model.addAttribute("board",boardsService.findBoardByName(boardName));
            model.addAttribute("publication", publication);
            return "/boards/boardPage";
        }

        publication.setBoard(boardsService.findBoardByName(boardName));
        publicationsService.savePublication(publication);
        return "redirect:/boards/"+boardName +"/"+publication.getId();
    }


    //получить страницу публикации в борде
    @GetMapping("/{publicationId}")
    public String getPublicationPageInBoard(@PathVariable("boardName") String boardName,
                                            @PathVariable("publicationId") int publicationId,
                                            Model model) {
        Comment comment = new Comment();
        comment.setParentComment(new Comment());

        model.addAttribute("publication",publicationsService.findPublicationById(publicationId));
        model.addAttribute("comments",commentsService.getCommentsForShowingInPublication(publicationId));
        model.addAttribute("comment", comment);

        return "/publications/publicationPage";
    }



}


