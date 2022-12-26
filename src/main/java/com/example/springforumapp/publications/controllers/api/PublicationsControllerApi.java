package com.example.springforumapp.publications.controllers.api;


import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInputDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutputDTO;
import com.example.springforumapp.publications.services.PublicationsService;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/publications")
public class PublicationsControllerApi {

    private final PublicationsService publicationsService;
    private final BoardsService boardsService;
    private final ModelMapper modelMapper;

    @Autowired
    public PublicationsControllerApi(PublicationsService publicationsService, BoardsService boardsService, ModelMapper modelMapper) {
        this.publicationsService = publicationsService;
        this.boardsService = boardsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api/publications")
    public ResponseEntity<?> getAllPublicationsApi(){
        List<PublicationOutputDTO> dtos = publicationsService.getAllPublications()
                .stream()
                .map(publication -> {
                    UserDTO userDTO = modelMapper.map(publication.getUser(), UserDTO.class);
                    PublicationOutputDTO publicationOutputDTO = modelMapper.map(publication, PublicationOutputDTO.class);
                    publicationOutputDTO.setUserDTO(userDTO);
                    return publicationOutputDTO;
                        })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/boards/{boardName}")
    public ResponseEntity<?> getAllPublicationsInBoardApi(@PathVariable("boardName") String boardName){
        List<PublicationOutputDTO> dtos = publicationsService.getAllPublicationsByBoardName(boardName)
                .stream()
                .map(publication -> modelMapper.map(publication, PublicationOutputDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/api/boards/{boardName}")
    public ResponseEntity<?> createPublicationInBoardApi(@PathVariable("boardName") String boardName,
                                                         @RequestBody @Valid PublicationInputDTO publicationInputDTO,
                                                         BindingResult bindingResult)

    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        Publication publication = modelMapper.map(publicationInputDTO, Publication.class);
        publication.setBoard(boardsService.findBoardByName(boardName));
        publication.setUser(userDetailsImpl.getUser());

        publicationsService.savePublication(publication);

        return ResponseEntity.ok("");
    }
}
