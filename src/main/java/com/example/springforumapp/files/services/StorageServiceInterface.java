package com.example.springforumapp.files.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageServiceInterface {
    void init();

    void store(MultipartFile file, String newFileName);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);
    void deleteAll();

}
