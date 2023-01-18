package com.example.springforumapp.publications.services;

import com.example.springforumapp.boards.models.domain.Board;
import com.example.springforumapp.boards.services.BoardsService;
import com.example.springforumapp.comments.models.domain.Comment;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.repositories.PublicationsRepository;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.users.models.domain.User;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PublicationsService {
    private final PublicationsRepository publicationsRepository;
    private final BoardsService boardsService;

    public List<Publication> getAllPublications(){
        return publicationsRepository.findAll();
    }
    public List<Publication> getAllPublicationsByPage(int pageNum, int pageSize,String sortType, String sortBy) throws PublicationException{
        try{
            return publicationsRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(sortType),sortBy))).getContent();
        } catch (Exception exception){
            throw new PublicationException("Please, specify right url parameters", "PublicationService.java: PublicationException");
        }
    }

    public List<Publication> getAllBoardPublicationsByPage(String boardName, int pageNum, int pageSize,String sortType, String sortBy) throws PublicationException{
        Board board = boardsService.findByName(boardName);
        try{
            return publicationsRepository.findAllByBoardName(boardName, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(sortType),sortBy))).getContent();
        } catch (Exception exception){
            throw new PublicationException("Please, specify right url parameters", "PublicationService.java: PublicationException");
        }
    }

    public Publication getPublication(int publicationId, Board board) throws PublicationException{
        Optional<Publication> publication = publicationsRepository.findByIdAndBoardName(publicationId, board.getName());
        if(publication.isEmpty())
            throw new PublicationException("Such publication does not is", "PublicationService.java: PublicationException");

        return publication.get();
    }

    @Transactional
    public void savePublication(Publication publication){
        publication.setDateOfCreation(LocalDate.now());
        publicationsRepository.save(publication);
    }

    @Transactional
    public void deletePublication(User user, int publicationId) throws PublicationException{
        Optional<Publication> publication = publicationsRepository.findById(publicationId);
        if (publication.isEmpty()){
           throw new PublicationException("Such publication does not exist","user write id of not exist publication");
        }
        if (user.getId() == publication.get().getUser().getId()) {
            publicationsRepository.delete(publication.get());
        } else {
            throw new PublicationException("It is not your publication", "user tries to delete foreign publication");
        }
    }
}
