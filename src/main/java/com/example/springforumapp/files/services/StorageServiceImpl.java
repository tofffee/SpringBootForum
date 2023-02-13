package com.example.springforumapp.files.services;


import com.example.springforumapp.files.models.UpFileType;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.util.FileUtil;
import com.example.springforumapp.files.util.exceptions.FileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final FileUtil fileUtil;
    private final String imagesFolderPath = "uploads/images/";
    private final String videoFolderPath = "uploads/videos/";
    @Value("${public.scheme}")
    String scheme;
    @Value("${public.hostname}")
    String hostName;
    @Value("${server.port}")
    String port;

    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(imagesFolderPath));
            Files.createDirectories(Paths.get(videoFolderPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Transactional
    @Override
    public UpFile store(MultipartFile file) {
                Path uploadLocationPath = null;

                UpFile upFile = new UpFile();

                String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase(); //  if file has no extension or invalid extension, getExtension will return ""
                if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")){
                    upFile.setType(UpFileType.IMAGE);
                } else if (extension.equals("mp4") || extension.equals("webm")){
                    upFile.setType(UpFileType.VIDEO);
                } else throw new FileException("Please choose picture or video file","StrorageService.java: FileException");

                String newFileName = fileUtil.generateRandomFileName(file);
                upFile.setName(newFileName);
                switch (upFile.getType()) {
                    case IMAGE -> {
                        upFile.setUrl(scheme + hostName + port + "/" + imagesFolderPath + newFileName);
                        uploadLocationPath = Paths.get(imagesFolderPath);
                    }
                    case VIDEO -> {
                        upFile.setUrl(scheme + hostName + port +  "/" + videoFolderPath + newFileName);
                        uploadLocationPath = Paths.get(videoFolderPath);
                    }
                }
            try {
                Files.copy(file.getInputStream(), uploadLocationPath.resolve(newFileName));
                return upFile;
            } catch (Exception e) {
            throw new FileException("File can not be uploaded","StrorageService.java: FileException");
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
    public void deleteAll() { }

    @Transactional
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
