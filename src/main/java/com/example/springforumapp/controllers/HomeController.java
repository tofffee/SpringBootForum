package com.example.springforumapp.controllers;

import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.services.BoardsService;
import com.example.springforumapp.util.IdRandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class HomeController {

    private final BoardsService boardsService;

    @Autowired
    public HomeController(BoardsService boardsService) {
        this.boardsService = boardsService;
    }

    @GetMapping("")
    public String getHomePage(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("boardsList", boardsService.getAllBoards());
        return "homePage";
    }

}
