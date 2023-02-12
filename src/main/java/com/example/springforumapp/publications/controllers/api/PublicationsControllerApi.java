package com.example.springforumapp.publications.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.publications.facades.PublicationFacadeImpl;
import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.publications.services.PublicationsServiceImpl;
import com.example.springforumapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicationsControllerApi {
    private final PublicationsServiceImpl publicationsService;
    private final PublicationFacadeImpl publicationFacade;

    private final String defaultPage = "0";
    private final String defaultPageSize = "3";
    private final String defaultSortType = "asc";
    private final String defaultSortBy = "id";
    @GetMapping("/publications")
    public ResponseEntity<ResponseApi> getAllPublicationsByPageApi(
            @RequestParam(name = "page" ,required = false, defaultValue = defaultPage) int pageNum,
            @RequestParam(name = "size", required = false, defaultValue = defaultPageSize) int pageSize,
            @RequestParam(name = "sortType",required = false,  defaultValue = defaultSortType) String sortType,
            @RequestParam(name = "sortBy",required = false, defaultValue = defaultSortBy) String sortBy){
        List<PublicationOutDTO> dtos = publicationsService.findAllPublicationsByPage(pageNum,pageSize, sortType, sortBy);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dtos));
    }

    @GetMapping("/boards/{boardName}")
    public ResponseEntity<ResponseApi> getAllPublicationsInBoardByPageApi(
            @PathVariable("boardName") String boardName,
            @RequestParam(name = "page" ,required = false, defaultValue = defaultPage) int pageNum,
            @RequestParam(name = "size", required = false, defaultValue = defaultPageSize) int pageSize,
            @RequestParam(name = "sortType",required = false,  defaultValue = defaultSortType) String sortType,
            @RequestParam(name = "sortBy",required = false, defaultValue = defaultSortBy) String sortBy) {
        List<PublicationOutDTO> dtos = publicationFacade.findAllPublicationsInBoardByPage(boardName, pageNum, pageSize, sortType, sortBy);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dtos));
    }

    @GetMapping("/boards/{boardName}/{publicationId}")
    public ResponseEntity<ResponseApi> getPublicationByIdInBoardApi(
            @PathVariable("boardName") String boardName,
            @PathVariable("publicationId") long publicationId) {
        PublicationOutDTO dto = publicationFacade.findPublicationsByIdInBoard(publicationId, boardName);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }
    @PostMapping("/boards/{boardName}")
    public ResponseEntity<ResponseApi> createPublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @PathVariable("boardName") String boardName,
                                                                   @RequestBody @Valid PublicationInDTO publicationInDTO) {
        PublicationOutDTO dto = publicationFacade.savePublication(userDetails, boardName, publicationInDTO);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
    }

//    @DeleteMapping("/boards/{boardName}/{publicationId}")
//    public ResponseEntity<ResponseApi> deletePublicationInBoardApi(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//                                                         @PathVariable("boardName") String boardName,
//                                                         @PathVariable("publicationId") long publicationId) {
//        publicationsService.deletePublication(userDetailsImpl.getUser(), publicationId);
//        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "publication deleted successfully"));
//    }
}
