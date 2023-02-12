package com.example.springforumapp.files.services;



import com.example.springforumapp.files.models.domain.UpFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    public void init();

    public UpFile store(MultipartFile file);

    public Stream<Path> loadAll();

    public Path load(String filename);

    public Resource loadAsResource(String filename);
    public void deleteAll();
    public void delete(String fileName);

}
