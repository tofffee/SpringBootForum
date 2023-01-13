package com.example.springforumapp.publications.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.repositories.PublicationsRepository;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PublicationsService {
    private final PublicationsRepository publicationsRepository;
    private final BoardsService boardsService;

    @Autowired
    public PublicationsService(PublicationsRepository publicationsRepository, BoardsService boardsService) {
        this.publicationsRepository = publicationsRepository;
        this.boardsService = boardsService;
    }

    public List<Publication> getAllPublications(){
        return publicationsRepository.findAll();
    }
    public List<Publication> getAllPublicationsByPage(int pageNum, int pageSize,String sortType, String sortBy){
        try{
            return publicationsRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(sortType),sortBy))).getContent();
        } catch (Exception exception){
            throw new PublicationException("Please, specify right url parameters", "PublicationController.java: PublicationException");
        }
    }

    public List<Publication> getAllPublicationsByBoardName(String boardName){
        Board board = boardsService.findBoardByName(boardName);
        return publicationsRepository.findAllByBoardName(board.getName());
    }

    @Transactional
    public void savePublication(Publication publication){
        publication.setDateOfCreation(LocalDate.now());
        publicationsRepository.save(publication);
    }
    @Transactional
    public void deletePublication(User user, int publicationId){
        Optional<Publication> publication = publicationsRepository.findById(publicationId);
        if (publication.isEmpty()){
           throw new PublicationException("Such publication doe not exist","user write id of not exist publication");
        }
        if (user.getId() == publication.get().getUser().getId()) {
            publicationsRepository.delete(publication.get());
        } else {
            throw new PublicationException("It is not your publication", "user tries to delete foreign publication");
        }
    }
}
