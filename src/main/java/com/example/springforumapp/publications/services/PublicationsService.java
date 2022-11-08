package com.example.springforumapp.publications.services;

import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.publications.repositories.PublicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class PublicationsService {
    private final PublicationsRepository publicationsRepository;

    @Autowired
    public PublicationsService(PublicationsRepository publicationsRepository) {
        this.publicationsRepository = publicationsRepository;
    }

    public Publication findPublicationById(int id){
        Optional<Publication> publication = publicationsRepository.findById(id);
        if(publication.isPresent())
            return publication.get();
        else return null;
    }

    public void savePublication(Publication publication){
        publication.setTimeOfPublication(LocalDate.now());
        publicationsRepository.save(publication);
    }
}
