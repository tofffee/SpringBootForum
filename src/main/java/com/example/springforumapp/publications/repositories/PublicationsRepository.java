package com.example.springforumapp.publications.repositories;

import com.example.springforumapp.publications.models.domain.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends JpaRepository<Publication,Integer> {
}
