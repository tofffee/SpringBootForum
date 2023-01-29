package com.example.springforumapp.comments.controllers.api;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.models.dto.CommentInputDTO;
import com.example.springforumapp.comments.models.dto.CommentOutputDTO;
import com.example.springforumapp.comments.services.CommentsService;
import com.example.springforumapp.comments.util.validators.CommentValidator;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.services.PublicationsService;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentsControllerApi {
    private final CommentsService commentsService;
    private final BoardsService boardsService;
    private final PublicationsService publicationsService;
    private final CommentValidator commentValidator;
    private final ModelMapper modelMapper;

    @PostMapping("/boards/{boardName}/{publicationId}")
    public ResponseEntity<ResponseApi> addCommentToPublicationApi(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") long publicationId,
            @RequestBody @Valid CommentInputDTO commentInputDTO,
            BindingResult bindingResult){
        commentValidator.validate(commentInputDTO, bindingResult);

        Board board = boardsService.findByName(boardName);
        Publication publication = publicationsService.getPublication(publicationId, board);

        Comment comment = modelMapper.map(commentInputDTO, Comment.class);
        commentsService.addComment(comment, publication, userDetails.getUser());

        CommentOutputDTO dto = commentToOutputDTO(comment);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }

    private CommentOutputDTO commentToOutputDTO(Comment comment){
        CommentOutputDTO commentOutputDTO = modelMapper.map(comment, CommentOutputDTO.class);
        UserDTO userDTO = modelMapper.map(comment.getUser(), UserDTO.class);
        commentOutputDTO.setUserDTO(userDTO);
        return commentOutputDTO;
    }
}
