package com.example.springforumapp.services;

import com.example.springforumapp.models.Publication;
import com.example.springforumapp.repositories.PublicationsRepository;
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

    public Optional<Publication> findPublicationById(int id){
        return publicationsRepository.findById(id);
    }

    public void savePublication(Publication publication){
        publication.setTimeOfPublication(LocalDate.now());
        publicationsRepository.save(publication);
    }
}
