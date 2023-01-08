package com.example.springforumapp.publications.controllers.api;


import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.dto.ImageDTO;
import com.example.springforumapp.files.services.ImagesService;
import com.example.springforumapp.files.services.StorageService;
import com.example.springforumapp.files.util.FileUtil;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api/")
public class PublicationsControllerApi {

    private final PublicationsService publicationsService;
    private final BoardsService boardsService;
    private final StorageService storageService;
    private final ImagesService imagesService;
    private final PublicationValidator publicationValidator;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public PublicationsControllerApi(PublicationsService publicationsService, BoardsService boardsService, StorageService storageService, ImagesService imagesService, PublicationValidator publicationValidator, ModelMapper modelMapper, FileUtil fileUtil) {
        this.publicationsService = publicationsService;
        this.boardsService = boardsService;
        this.storageService = storageService;
        this.imagesService = imagesService;
        this.publicationValidator = publicationValidator;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @GetMapping("/publications")
    public ResponseEntity<?> getAllPublicationsApi(){
        List<Publication> publications = publicationsService.getAllPublications();
        List<PublicationOutputDTO> publicationsDTOs = publicationsToPublicationsOutputDTO(publications);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),publicationsDTOs));
    }

    @GetMapping("/boards/{boardName}")
    public ResponseEntity<?> getAllPublicationsInBoardApi(@PathVariable("boardName") String boardName){
        List<Publication> publications = publicationsService.getAllPublicationsByBoardName(boardName);
        List<PublicationOutputDTO> publicationsDTOs = publicationsToPublicationsOutputDTO(publications);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), publicationsDTOs));
    }

    @RequestMapping(path = "/boards/{boardName}",method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseApi> createPublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                                   @PathVariable("boardName") String boardName,
                                                                   @RequestPart("dto") @Valid PublicationInputDTO publicationInputDTO,
                                                                   @RequestPart("file")  MultipartFile[] images,
                                                                   BindingResult bindingResult)
    {
        publicationValidator.validate(publicationInputDTO, bindingResult);

        Publication publication = modelMapper.map(publicationInputDTO, Publication.class);
        publication.setBoard(boardsService.findBoardByName(boardName));
        publication.setUser(userDetailsImpl.getUser());

//        String newImageName = fileUtil.generateRandomImageName(image);
//        storageService.store(image, newImageName);
//        publicationsService.savePublication(publication);
//        imagesService.saveImage(publication, newImageName);


        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication successfully created"));
    }

    @DeleteMapping("/boards/{boardName}/{publicationId}")
    public ResponseEntity<?> deletePublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                         @PathVariable("boardName") String boardName,
                                                         @PathVariable("publicationId") int publicationId) {

        publicationsService.deletePublication(userDetailsImpl.getUser(), publicationId);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication deleted successfully"));
    }

    private List<PublicationOutputDTO> publicationsToPublicationsOutputDTO(List<Publication> publications){
        return publications
                .stream()
                .map(publication -> {
                    PublicationOutputDTO publicationOutputDTO = modelMapper.map(publication, PublicationOutputDTO.class);
                    UserDTO userDTO = modelMapper.map(publication.getUser(), UserDTO.class);
                    publicationOutputDTO.setUserDTO(userDTO);
                    var a = publication.getImages().get(0);
                    ImageDTO imageDTO = modelMapper.map(a, ImageDTO.class);
                    publicationOutputDTO.setPictureDTO(imageDTO);
                    return publicationOutputDTO;
                })
                .collect(Collectors.toList());
    }
}
