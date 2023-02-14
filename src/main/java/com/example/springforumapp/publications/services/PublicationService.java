package com.example.springforumapp.publications.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.models.dto.PublicationInDTO;
import com.example.springforumapp.publications.models.dto.PublicationOutDTO;
import com.example.springforumapp.users.models.domain.User;

import java.util.List;

public interface PublicationService {
    public List<PublicationOutDTO> findAllPublicationsByPage(int pageNum, int pageSize, String sortType, String sortBy);

    public List<PublicationOutDTO> findAllPublicationsInBoardByPage(Board board, int pageNum, int pageSize, String sortType, String sortBy);

    public Publication findPublicationByIdInBoard(Board board, long publicationId);

    public PublicationOutDTO savePublication(User user, Board board, PublicationInDTO inDto,List<UpFile> upFiles);

    public void deletePublication(User user, long publicationId);
}
