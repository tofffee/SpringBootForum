package com.example.springforumapp.comments.facades;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.services.BoardsServiceImpl;
import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.models.dto.CommentInDTO;
import com.example.springforumapp.comments.models.dto.CommentOutDTO;
import com.example.springforumapp.comments.services.CommentsServiceImpl;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.services.PublicationsServiceImpl;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentsFacadeImpl implements CommentsFacade{
    private final UsersServiceImpl usersService;
    private final BoardsServiceImpl boardsService;
    private final PublicationsServiceImpl publicationsService;
    private final CommentsServiceImpl commentsService;

    @Override
    public List<CommentOutDTO> findCommentsByPublicationId(String boardName, long publicationId){
        Board board = boardsService.findByName(boardName);
        Publication publication = publicationsService.findPublicationByIdInBoard(board,publicationId);
        return commentsService.findCommentsByPublication(publication);
    }

    @Override
    public CommentOutDTO createComment(UserDetailsImpl userDetails, String boardName, long publicationId, CommentInDTO commentInDTO){
        User user = usersService.findByUsername(userDetails.getUsername());
        Board board = boardsService.findByName(boardName);
        Publication publication = publicationsService.findPublicationByIdInBoard(board, publicationId);
        return commentsService.createComment(user,publication,commentInDTO);
    }
}
