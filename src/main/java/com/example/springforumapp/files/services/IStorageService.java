package com.example.springforumapp.files.services;



import com.example.springforumapp.files.models.domain.UpFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    void init();

    UpFile store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);
    void deleteAll();
    void delete(String fileName);

}
