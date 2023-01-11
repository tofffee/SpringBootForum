package com.example.springforumapp.publications.controllers.api;


import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.domain.Image;
import com.example.springforumapp.files.models.dto.ImageDTO;
import com.example.springforumapp.files.services.ImagesService;
import com.example.springforumapp.files.services.StorageService;
import com.example.springforumapp.files.util.FileUtil;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.publications.services.PublicationsService;
import com.example.springforumapp.publications.util.validators.PublicationValidator;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.dto.UserDTO;
import com.example.springforumapp.users.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class PublicationsControllerApi {

    private final PublicationsService publicationsService;
    private final BoardsService boardsService;
    private final UsersService usersService;
    private final StorageService storageService;
    private final ImagesService imagesService;
    private final PublicationValidator publicationValidator;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public PublicationsControllerApi(PublicationsService publicationsService, BoardsService boardsService, UsersService usersService, StorageService storageService, ImagesService imagesService, PublicationValidator publicationValidator, ModelMapper modelMapper, FileUtil fileUtil) {
        this.publicationsService = publicationsService;
        this.boardsService = boardsService;
        this.usersService = usersService;
        this.storageService = storageService;
        this.imagesService = imagesService;
        this.publicationValidator = publicationValidator;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @GetMapping("/publications")
    public ResponseEntity<?> getAllPublicationsApi(){
        List<Publication> publications = publicationsService.getAllPublications();
        List<PublicationOutDTO> publicationsDTOs = publicationsToPublicationsOutputDTO(publications);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),publicationsDTOs));
    }

    @GetMapping("/boards/{boardName}")
    public ResponseEntity<?> getAllPublicationsInBoardApi(@PathVariable("boardName") String boardName){
        List<Publication> publications = publicationsService.getAllPublicationsByBoardName(boardName);
        List<PublicationOutDTO> publicationsDTOs = publicationsToPublicationsOutputDTO(publications);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), publicationsDTOs));
    }

    @PostMapping(path = "/boards/{boardName}")
    public ResponseEntity<ResponseApi> createPublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @PathVariable("boardName") String boardName,
                                                                   @RequestBody @Valid PublicationInDTO publicationInDTO,
                                                                   BindingResult bindingResult)
    {
        publicationValidator.validate(publicationInDTO, bindingResult);

        Publication publication = convertPublicationInDtoToPublication(publicationInDTO);
        for(int id:publicationInDTO.getImagesId()){
            Image image = imagesService.findImageById(id);
            publication.addImage(image);
        }
        publication.setBoard(boardsService.findBoardByName(boardName));
        publication.setUser(usersService.findBId(userDetails.getUser().getId()));
        publicationsService.savePublication(publication);

        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication successfully created"));
    }

    @DeleteMapping("/boards/{boardName}/{publicationId}")
    public ResponseEntity<?> deletePublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                         @PathVariable("boardName") String boardName,
                                                         @PathVariable("publicationId") int publicationId) {

        publicationsService.deletePublication(userDetailsImpl.getUser(), publicationId);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication deleted successfully"));
    }

    private List<PublicationOutDTO> publicationsToPublicationsOutputDTO(List<Publication> publications){
        return publications
                .stream()
                .map(publication -> {
                    PublicationOutDTO publicationOutDTO = modelMapper.map(publication, PublicationOutDTO.class);
                    UserDTO userDTO = modelMapper.map(publication.getUser(), UserDTO.class);
                    publicationOutDTO.setUserDTO(userDTO);
                    var a = publication.getImages().get(0);
                    ImageDTO imageDTO = modelMapper.map(a, ImageDTO.class);
                    publicationOutDTO.setPictureDTO(imageDTO);
                    return publicationOutDTO;
                })
                .collect(Collectors.toList());
    }

    private Publication convertPublicationInDtoToPublication(PublicationInDTO dto){
        Publication publication = new Publication();
        publication.setName(dto.getName());
        publication.setText(dto.getText());
        return publication;
    }
}
