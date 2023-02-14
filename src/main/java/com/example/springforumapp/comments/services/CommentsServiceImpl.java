package com.example.springforumapp.comments.services;

import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.comments.models.dto.CommentInDTO;
import com.example.springforumapp.comments.models.dto.CommentOutDTO;
import com.example.springforumapp.comments.repositories.CommentsRepository;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.models.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CommentOutDTO> findCommentsByPublication(Publication publication){
        List<Comment> comments = commentsRepository.findAllByPublicationId(publication.getId());
        return commentsToOutDtos(comments);
    }
    @Override
    @Transactional
    public CommentOutDTO createComment(User user, Publication publication, CommentInDTO commentInDTO){
        Comment comment = modelMapper.map(commentInDTO, Comment.class);
        comment.setPublication(publication);
        comment.setUser(user);
        comment.setCreatedAt(LocalDate.now());
        commentsRepository.save(comment);
        return commentToOutDTO(comment);
    }

    private CommentOutDTO commentToOutDTO(Comment comment){
        CommentOutDTO commentOutputDTO = modelMapper.map(comment, CommentOutDTO.class);
        UserDTO userDTO = modelMapper.map(comment.getUser(), UserDTO.class);
        commentOutputDTO.setUserDTO(userDTO);
        return commentOutputDTO;
    }

    private List<CommentOutDTO> commentsToOutDtos(List<Comment> comments){
        return comments
                    .stream()
                .map(this::commentToOutDTO)
                .collect(Collectors.toList());
    }


}
