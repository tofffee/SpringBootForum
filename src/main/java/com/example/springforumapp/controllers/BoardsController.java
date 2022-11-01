package com.example.springforumapp.controllers;

import com.example.springforumapp.models.Board;
import com.example.springforumapp.models.Publication;
import com.example.springforumapp.models.User;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.services.BoardsService;
import com.example.springforumapp.services.PublicationsService;
import com.example.springforumapp.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/boards")
public class BoardsController {

    private final BoardsService boardsService;
    private final PublicationsService publicationsService;

    private final UsersService usersService;

    @Autowired
    public BoardsController(BoardsService boardsService, PublicationsService publicationsService, UsersService usersService)
    {
        this.boardsService = boardsService;
        this.publicationsService = publicationsService;
        this.usersService = usersService;
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
            )
    {
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
