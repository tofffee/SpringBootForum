package com.example.springforumapp.repositories;

import com.example.springforumapp.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends JpaRepository<Publication,Integer> {
}
