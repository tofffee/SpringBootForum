//package com.example.springforumapp.users.controllers.web;
//
//import com.example.springforumapp.users.models.domain.User;
//import com.example.springforumapp.security.UserDetailsImpl;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/profile")
//public class ProfileController {
//
//    @GetMapping("")
//    public String getProfilePage(Model model){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
//        User user = userDetailsImpl.getUser();
//
//        model.addAttribute("user",user);
//
//        return "profile/profilePage";
//    }
//
////        @GetMapping("")
////        public String getProfilePage( @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model){
////            model.addAttribute("user",userDetailsImpl);
////            return "profile/profilePage";
////     }
//
//
//}
//
