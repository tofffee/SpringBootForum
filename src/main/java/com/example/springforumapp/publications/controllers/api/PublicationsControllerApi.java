package com.example.springforumapp.publications.controllers.api;


import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.files.services.UpFileService;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.publications.services.PublicationsService;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.publications.util.validators.PublicationValidator;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.dto.UserDTO;
import com.example.springforumapp.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicationsControllerApi {
    private final PublicationsService publicationsService;
    private final BoardsService boardsService;
    private final UsersService usersService;
    private final UpFileService upFileService;
    private final PublicationValidator publicationValidator;
    private final ModelMapper modelMapper;
    private final String defaultPageSize = "3";

    @GetMapping("/publications")
    public ResponseEntity<ResponseApi> getAllPublicationsByPageApi(
            @RequestParam(name = "page" ,required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "size", required = false, defaultValue = defaultPageSize) int pageSize,
            @RequestParam(name = "sortType",required = false,  defaultValue = "asc") String sortType,
            @RequestParam(name = "sortBy",required = false, defaultValue = "id") String sortBy){
        List<Publication> publications = publicationsService.getAllPublicationsByPage(pageNum,pageSize, sortType, sortBy);
        List<PublicationOutDTO> dtos = publicationsToOutDTOs(publications);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(),dtos));
    }

    @GetMapping("/boards/{boardName}")
    public ResponseEntity<ResponseApi> getAllPublicationsInBoardByPageApi(
            @PathVariable("boardName") String boardName,
            @RequestParam(name = "page" ,required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "size", required = false, defaultValue = defaultPageSize) int pageSize,
            @RequestParam(name = "sortType",required = false,  defaultValue = "asc") String sortType,
            @RequestParam(name = "sortBy",required = false, defaultValue = "id") String sortBy) {
        List<Publication> publications = publicationsService.getAllBoardPublicationsByPage(boardName,pageNum,pageSize, sortType, sortBy);
        List<PublicationOutDTO> dtos = publicationsToOutDTOs(publications);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dtos));
    }

    @GetMapping("/boards/{boardName}/{publicationId}")
    public ResponseEntity<ResponseApi> getPublication(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") long publicationId) {
        Board board = boardsService.findByName(boardName);
        Publication publication = publicationsService.getPublication(publicationId, board);

        PublicationOutDTO dto = publicationToOutDTO(publication);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }
    @PostMapping(path = "/boards/{boardName}")
    public ResponseEntity<ResponseApi> createPublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @PathVariable("boardName") String boardName,
                                                                   @RequestBody @Valid PublicationInDTO publicationInDTO,
                                                                   BindingResult bindingResult)
    {
        publicationValidator.validate(publicationInDTO, bindingResult);

        Publication publication = convertInDtoToPublication(publicationInDTO);
        publication.setBoard(boardsService.findByName(boardName));
        publication.setUser(usersService.findById(userDetails.getUser().getId()));
        publicationsService.savePublication(publication);

        PublicationOutDTO dto = publicationToOutDTO(publication);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
    }

    @DeleteMapping("/boards/{boardName}/{publicationId}")
    public ResponseEntity<ResponseApi> deletePublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                         @PathVariable("boardName") String boardName,
                                                         @PathVariable("publicationId") long publicationId) {
        publicationsService.deletePublication(userDetailsImpl.getUser(), publicationId);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "publication deleted successfully"));
    }



    private PublicationOutDTO publicationToOutDTO(Publication publication){
        PublicationOutDTO dto = new PublicationOutDTO();
        dto.setId(publication.getId());
        dto.setUserDTO(modelMapper.map(publication.getUser(),UserDTO.class));
        dto.setName(publication.getName());
        dto.setText(publication.getText());
        dto.setCreatedAt(publication.getCreatedAt());
        if(publication.getUpfiles() != null && !publication.getUpfiles().isEmpty())
            dto.setUpFilesOutDtos(modelMapper.map(publication.getUpfiles(),new TypeToken<List<UpFileOutDTO>>(){}.getType()));
         else
            dto.setUpFilesOutDtos(new ArrayList<>());
        return dto;
    }

    private List<PublicationOutDTO> publicationsToOutDTOs(List<Publication> publications){
        return publications
                .stream()
                .map(this::publicationToOutDTO)
                .collect(Collectors.toList());
    }

    private Publication convertInDtoToPublication(PublicationInDTO dto){
        Publication publication = new Publication();
        publication.setName(dto.getName());
        publication.setText(dto.getText());
        if(dto.getUpFilesId()!=null && !dto.getUpFilesId().isEmpty()){
            List<UpFile> upfiles = new ArrayList<>();
            dto.getUpFilesId().forEach(id ->upfiles.add(upFileService.findFileById(id)));
            publication.setUpfiles(upfiles);
        }
        return publication;
    }
}
