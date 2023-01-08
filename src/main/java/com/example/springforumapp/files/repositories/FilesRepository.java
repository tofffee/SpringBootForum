package com.example.springforumapp.files.repositories;

import com.example.springforumapp.files.models.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<File, Integer> {
}
