package com.example.springforumapp.files.services;


import com.example.springforumapp.files.util.exceptions.FileNotSavedException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@Transactional
public class StorageService implements IStorageService {
    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file,String newFileName) {
        try {
                Path uploadImagesLocationPath = Paths.get("upload/images");
                Files.copy(file.getInputStream(), uploadImagesLocationPath.resolve(newFileName));
            } catch (Exception e) {
            throw new FileNotSavedException("file can not be uploaded","StrorageService.java :FileNotSavedException");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
