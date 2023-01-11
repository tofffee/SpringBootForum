package com.example.springforumapp.files.services;


import com.example.springforumapp.files.util.FileUtil;
import com.example.springforumapp.files.util.exceptions.FileException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final FileUtil fileUtil;

    @Autowired
    public StorageService(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void init() {

    }

    @Override
    public String store(MultipartFile file) {
        try {
                Path uploadImagesLocationPath = Paths.get("upload/images");
                String newImageName = fileUtil.generateRandomImageName(file);
                Files.copy(file.getInputStream(), uploadImagesLocationPath.resolve(newImageName));
                return newImageName;
            } catch (Exception e) {
            throw new FileException("file can not be uploaded","StrorageService.java :FileException");
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

    @Override
    public void delete(String fileName) {
        try {
            Path file = Paths.get("upload/images").resolve(fileName);
            Files.delete(file);
        } catch (Exception e) {
            throw new FileException("file can not be deleted","StrorageService.java :FileException");
        }
    }

}
