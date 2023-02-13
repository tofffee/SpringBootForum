package com.example.springforumapp.comments.controllers.api;

import com.example.springforumapp.boards.services.BoardsServiceImpl;
import com.example.springforumapp.comments.services.CommentsServiceImpl;
import com.example.springforumapp.publications.services.PublicationsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CommentsControllerApi {
    private final CommentsServiceImpl commentsServiceImpl;
    private final BoardsServiceImpl boardsService;
    private final PublicationsServiceImpl publicationsServiceImpl;
    private final ModelMapper modelMapper;

//    @PostMapping("/boards/{boardName}/{publicationId}")
//    public ResponseEntity<ResponseApi> addCommentToPublicationApi(
//            @AuthenticationPrincipal UserDetailsImpl userDetails,
//            @PathVariable("boardName") String boardName,
//            @PathVariable("publicationId") long publicationId,
//            @RequestBody @Valid CommentInputDTO commentInputDTO,
//            BindingResult bindingResult){
//        commentValidator.validate(commentInputDTO, bindingResult);
//
//        Board board = boardsService.findByName(boardName);
//        Publication publication = publicationsServiceImpl.getPublication(publicationId, board);
//
//        Comment comment = modelMapper.map(commentInputDTO, Comment.class);
//        commentsService.addComment(comment, publication, userDetails.getUser());
//
//        CommentOutputDTO dto = commentToOutputDTO(comment);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
//    }
//
//    private CommentOutputDTO commentToOutputDTO(Comment comment){
//        CommentOutputDTO commentOutputDTO = modelMapper.map(comment, CommentOutputDTO.class);
//        UserDTO userDTO = modelMapper.map(comment.getUser(), UserDTO.class);
//        commentOutputDTO.setUserDTO(userDTO);
//        return commentOutputDTO;
//    }
}
