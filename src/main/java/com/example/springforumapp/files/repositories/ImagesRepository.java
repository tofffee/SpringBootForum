package com.example.springforumapp.files.repositories;

import com.example.springforumapp.files.models.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Integer> {

}
