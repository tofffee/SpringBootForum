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


    @Autowired
    public BoardsController(BoardsService boardsService, PublicationsService publicationsService)
    {
        this.boardsService = boardsService;
        this.publicationsService = publicationsService;
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
            @Valid Publication publication,
            Model model,
            BindingResult bindingResult
            ){
        Board board = boardsService.findBoardByName(boardName);

        model.addAttribute("board",board);
        model.addAttribute("publication", publication);

        if(bindingResult.hasErrors()){
            return "/boards/boardPage";
        }else{
            publication.setBoard(board);
            publicationsService.savePublication(publication);
            return "redirect:/boards/"+boardName +"/"+publication.getId(); // перенаправление на страницу с новой публикацией в борде
        }

    }






}
