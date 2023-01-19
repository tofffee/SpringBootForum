package com.example.springforumapp.files.repositories;

import com.example.springforumapp.files.models.domain.UpFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpFilesRepository extends JpaRepository<UpFile, Long> {
}
