package com.example.springforumapp.controllers;

import com.example.springforumapp.models.Board;
import com.example.springforumapp.models.Publication;
import com.example.springforumapp.services.BoardsService;
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

    @Autowired
    public BoardsController(BoardsService boardsService, PublicationsService publicationsService) {
        this.boardsService = boardsService;
        this.publicationsService = publicationsService;
    }


    @GetMapping("/{boardName}")
    public String getBoardPage(@PathVariable("boardName") String boardName, Model model){
        Board board = boardsService.findBoardByName(boardName);
        model.addAttribute("board",board);
        model.addAttribute("publication",new Publication());
        return "/boards/boardPage";
    }

    @PostMapping("/{boardName}/create")
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
            return "redirect:/";
        }

    }

    @GetMapping("/{boardName}/{publicationId}")
    public String getPublicationPage(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") int publicationId,
            Model model
    ){
        Optional<Publication> publication = publicationsService.findPublicationById(publicationId);
        model.addAttribute("publication",publication.get());


        return "/boards/publications/publicationPage";
    }



}
