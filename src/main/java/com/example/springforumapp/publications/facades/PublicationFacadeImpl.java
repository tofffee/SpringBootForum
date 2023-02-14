package com.example.springforumapp.publications.facades;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.services.BoardsServiceImpl;
import com.example.springforumapp.boards.util.exceptions.BoardNotFoundException;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.services.UpFileServiceImpl;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.publications.services.PublicationsServiceImpl;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationFacadeImpl implements PublicationFacade{
    private final BoardsServiceImpl boardsService;
    private final PublicationsServiceImpl publicationsService;
    private final UsersServiceImpl usersService;
    private final UpFileServiceImpl upFileService;

    @Override
    public List<PublicationOutDTO> findAllPublicationsInBoardByPage(String boardName,
                                                                   int pageNum,
                                                                   int pageSize,
                                                                   String sortType,
                                                                   String sortBy) throws BoardNotFoundException, IllegalArgumentException {
        Board board = boardsService.findByName(boardName);
        return publicationsService.findAllPublicationsInBoardByPage(board, pageNum, pageSize, sortType, sortBy);
    }

    @Override
    public PublicationOutDTO findPublicationsByIdInBoard(long publicationId, String boardName) {
        Board board = boardsService.findByName(boardName);
        Publication publication = publicationsService.findPublicationByIdInBoard(board, publicationId);
        return publicationsService.publicationToOutDTO(publication);
    }


    @Override
    public PublicationOutDTO savePublication(UserDetailsImpl userDetails, String boardName, PublicationInDTO publicationInDTO){
        User user = usersService.findByUsername(userDetails.getUsername());
        Board board = boardsService.findByName(boardName);
        List<UpFile> upFiles = upFileService.findFilesByListIds(publicationInDTO.getUpFilesId());
        return publicationsService.savePublication(user, board, publicationInDTO, upFiles);
    }

}
