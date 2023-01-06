package com.example.springforumapp.publications.controllers.api;


import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInputDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutputDTO;
import com.example.springforumapp.publications.services.PublicationsService;
import com.example.springforumapp.publications.util.validators.PublicationValidator;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/publications")
public class PublicationsControllerApi {

    private final PublicationsService publicationsService;
    private final BoardsService boardsService;
    private final PublicationValidator publicationValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public PublicationsControllerApi(PublicationsService publicationsService, BoardsService boardsService, PublicationValidator publicationValidator, ModelMapper modelMapper) {
        this.publicationsService = publicationsService;
        this.boardsService = boardsService;
        this.publicationValidator = publicationValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/publications")
    public ResponseEntity<?> getAllPublicationsApi(){
        List<PublicationOutputDTO> publicationDTOs = publicationsService.getAllPublications()
                .stream()
                .map(publication -> {
                    UserDTO userDTO = modelMapper.map(publication.getUser(), UserDTO.class);
                    PublicationOutputDTO publicationDTO = modelMapper.map(publication, PublicationOutputDTO.class);
                    publicationDTO.setUserDTO(userDTO);
                    return publicationDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),publicationDTOs));
    }

    @GetMapping("/api/boards/{boardName}")
    public ResponseEntity<?> getAllPublicationsInBoardApi(@PathVariable("boardName") String boardName){
        List<PublicationOutputDTO> publicationsDTOs = publicationsService.getAllPublicationsByBoardName(boardName)
                .stream()
                .map(publication -> {
                    UserDTO userDTO = modelMapper.map(publication.getUser(), UserDTO.class);
                    PublicationOutputDTO publicationOutputDTO = modelMapper.map(publication, PublicationOutputDTO.class);
                    publicationOutputDTO.setUserDTO(userDTO);
                    return publicationOutputDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), publicationsDTOs));
    }

    @PostMapping("/api/boards/{boardName}")
    public ResponseEntity<?> createPublicationInBoardApi( @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                          @PathVariable("boardName") String boardName,
                                                          @RequestBody @Valid PublicationInputDTO publicationInputDTO,
                                                          BindingResult bindingResult)

    {
        publicationValidator.validate(publicationInputDTO, bindingResult);

        Publication publication = modelMapper.map(publicationInputDTO, Publication.class);
        publication.setBoard(boardsService.findBoardByName(boardName));
        publication.setUser(userDetailsImpl.getUser());
        publicationsService.savePublication(publication);

        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication created successfully"));
    }

    @DeleteMapping("/api/boards/{boardName}/{publicationId}")
    public ResponseEntity<?> deletePublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                         @PathVariable("boardName") String boardName,
                                                         @PathVariable("publicationId") int publicationId) {

        publicationsService.deletePublication(userDetailsImpl.getUser(), publicationId);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication deleted successfully"));
    }
}
