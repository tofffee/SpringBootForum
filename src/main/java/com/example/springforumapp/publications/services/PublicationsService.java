package com.example.springforumapp.publications.services;

import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.repositories.PublicationsRepository;
import com.example.springforumapp.publications.util.exceptions.PublicationException;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublicationsService {
    private final PublicationsRepository publicationsRepository;

    @Autowired
    public PublicationsService(PublicationsRepository publicationsRepository) {
        this.publicationsRepository = publicationsRepository;
    }

    public List<Publication> getAllPublications(){
        return publicationsRepository.findAll();
    }

    public List<Publication> getAllPublicationsByBoardName(String boardName){
        return publicationsRepository.findAllByBoardName(boardName);
    }

    public void savePublication(Publication publication){
        publication.setDateOfCreation(LocalDate.now());
        publicationsRepository.save(publication);
    }

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
