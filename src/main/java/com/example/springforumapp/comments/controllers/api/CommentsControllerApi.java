package com.example.springforumapp.comments.controllers.api;

import com.example.springforumapp.boards.services.BoardsServiceImpl;
import com.example.springforumapp.comments.facades.CommentsFacadeImpl;
import com.example.springforumapp.comments.models.dto.CommentInDTO;
import com.example.springforumapp.comments.models.dto.CommentOutDTO;
import com.example.springforumapp.comments.services.CommentsServiceImpl;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.publications.services.PublicationsServiceImpl;
import com.example.springforumapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CommentsControllerApi {
    private final CommentsFacadeImpl commentsFacade;

    @GetMapping ("/boards/{boardName}/{publicationId}/comments")
    public ResponseEntity<ResponseApi> getCommentsInPublicationApi(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") long publicationId){
        List<CommentOutDTO> dtos = commentsFacade.findCommentsByPublicationId(boardName, publicationId);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dtos));
    }

    @PostMapping("/boards/{boardName}/{publicationId}/comments")
    public ResponseEntity<ResponseApi> createCommentInPublicationApi(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") long publicationId,
            @RequestBody @Valid CommentInDTO commentInDTO){
        CommentOutDTO dto = commentsFacade.createComment(userDetails, boardName, publicationId, commentInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }

    @DeleteMapping("/boards/{boardName}/{publicationId}/comments")
    public ResponseEntity<ResponseApi> deleteCommentInPublicationApi(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") long publicationId,
            @RequestBody @Valid CommentInDTO commentInDTO){
         return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "dto"));
    }



}
