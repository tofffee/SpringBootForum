package com.example.springforumapp.home.controllers.web;

import com.example.springforumapp.boards.services.BoardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

    private final BoardsService boardsService;

    @Autowired
    public HomeController(BoardsService boardsService) {
        this.boardsService = boardsService;
    }

    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("boardsList", boardsService.getAllBoards());
        return "home/homePage";
    }

}
