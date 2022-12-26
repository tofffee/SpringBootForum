//package com.example.springforumapp.comments.controllers.web;
//
//import com.example.springforumapp.comments.models.domain.Comment;
//import com.example.springforumapp.comments.services.CommentsService;
//import com.example.springforumapp.publications.services.PublicationsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/boards/{boardName}/{publicationId}")
//public class CommentsController {
//
//
//    private final CommentsService commentsService;
//    private final PublicationsService publicationsService;
//
//    @Autowired
//    public CommentsController(CommentsService commentsService, PublicationsService publicationsService) {
//        this.commentsService = commentsService;
//        this.publicationsService = publicationsService;
//    }
//
//   //  создать комментарий в публикации в борде
//    @PostMapping()
//    public String addComment(@PathVariable("boardName") String boardName,
//                            @PathVariable("publicationId") int publicationId,
//                            Model model,
//                            @Valid Comment comment,
//                            BindingResult bindingResult){
//
//        model.addAttribute("publication",publicationsService.findPublicationById(publicationId));
//
//        if(bindingResult.hasErrors()){
//            model.addAttribute("comment",comment);
//            return "publications/publicationPage";
//        }
//
//        model.addAttribute("comment",new Comment());
//        comment.setPublication(publicationsService.findPublicationById(publicationId));
//
//
////        if(comment.getReplied_to_comment()!=null)
////        {
////            Comment replied_to_comment = commentsService.findCommentById(comment.getReplied_to_comment().getId());
////            comment.setReplied_to_comment(replied_to_comment);
////        }
//
//        commentsService.saveComment(comment);
//        return "publications/publicationPage";
//
//    }
//
//
////    // создать комментарий в публикации в борде REST
////    @ResponseBody
////    @PostMapping("")
////    public ResponseEntity<HttpStatus> addComment(
////            @PathVariable("boardName") String boardName,
////            @PathVariable("publicationId") int publicationId,
////            @RequestBody @Valid CommentDTO commentDTO,
////            BindingResult bindingResult
////    ) {
////
////        if (bindingResult.hasErrors()) {
////            StringBuilder errorMessage = new StringBuilder();
////            List<FieldError> errors = bindingResult.getFieldErrors();
////            for(FieldError error : errors){
////                errorMessage
////                        .append(error.getField())
////                        .append("-")
////                        .append(error.getDefaultMessage())
////                        .append("; ");
////            }
////
////            throw new CommentNotSavedException(errorMessage.toString());
////        }
////
////        Board board = boardsService.findBoardByName(boardName);
////        Optional<Publication> publication = publicationsService.findPublicationById(publicationId);
////
////        Comment comment = new Comment();
////        comment.setTextOfComment(commentDTO.getTextOfComment());
////        comment.setPublication(publication.get());
////        comment.setResponed_to_comment(commentDTO.getResponed_to_comment_id());
////        commentsService.saveComment(comment);
////
////        return ResponseEntity.ok(HttpStatus.OK);
////    }
////
////    @ExceptionHandler
////    private ResponseEntity<CommentResponseError> handleException(CommentNotSavedException commentNotSavedException)
////    {
////        CommentResponseError commentResponseError = new CommentResponseError(
////                commentNotSavedException.getMessage(),
////                System.currentTimeMillis()
////        );
////        return new ResponseEntity<>(commentResponseError, HttpStatus.BAD_REQUEST);
////    }
//
//}
