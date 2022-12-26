//package com.example.springforumapp.boards.controllers.web;
//
//import com.example.springforumapp.boards.models.domain.Board;
//import com.example.springforumapp.publications.models.domain.Publication;
//import com.example.springforumapp.boards.services.BoardsService;
//import com.example.springforumapp.publications.services.PublicationsService;
//import com.example.springforumapp.users.services.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/boards")
//public class BoardsController {
//
//    private final BoardsService boardsService;
//    private final PublicationsService publicationsService;
//
//    @Autowired
//    public BoardsController(BoardsService boardsService, PublicationsService publicationsService)
//    {
//        this.boardsService = boardsService;
//        this.publicationsService = publicationsService;
//    }
//
//
//    //Получить страницу борды
//    @GetMapping("/{boardName}")
//    public String getBoardPage(@PathVariable("boardName") String boardName, Model model){
//        model.addAttribute("board",boardsService.findBoardByName(boardName));
//        model.addAttribute("publication",new Publication());
//        return "/boards/boardPage";
//    }
//
//
//}
