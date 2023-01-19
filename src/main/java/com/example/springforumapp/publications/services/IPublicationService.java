package com.example.springforumapp.publications.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPublicationService {
    public List<Publication> getAllPublicationsByPage(int pageNum, int pageSize, String sortType, String sortBy);

    public List<Publication> getAllBoardPublicationsByPage(String boardName, int pageNum, int pageSize,String sortType, String sortBy);

    public Publication getPublication(long publicationId, Board board);

    public void savePublication(Publication publication);

    public void deletePublication(User user, long publicationId);
}
