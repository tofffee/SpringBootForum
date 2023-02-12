package com.example.springforumapp.publications.facades;

import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.security.UserDetailsImpl;

import java.util.List;

public interface PublicationFacade {
    public List<PublicationOutDTO> findAllPublicationsInBoardByPage(String boardName,
                                                                   int pageNum,
                                                                   int pageSize,
                                                                   String sortType,
                                                                   String sortBy);

    public PublicationOutDTO findPublicationsByIdInBoard(long publicationId, String boardName);
    public PublicationOutDTO savePublication(UserDetailsImpl userDetails,
                                             String boardName,
                                             PublicationInDTO publicationInDTO);
}
