package com.example.springforumapp.files.services;


import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.util.FileUtil;
import com.example.springforumapp.files.util.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final String imagesFolderPath = "upload/files/images/";
    private final String videoFolderPath = "upload/files/videos/";
    @Value("${server.hostname}")
    String hostName;
    @Autowired
    public StorageService(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void init() {

    }

    @Override
    public UpFile store(MultipartFile file) {
                Path uploadLocationPath = null;

                UpFile upFile = new UpFile();

                String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
                if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")){
                    upFile.setType("IMAGE");
                } else if (extension.equals("mp4") || extension.equals("webm")){
                    upFile.setType("VIDEO");
                } else throw new FileException("Please choose picture or video file","StrorageService.java :FileException");

                String newFileName = fileUtil.generateRandomFileName(file);
                upFile.setName(newFileName);
                switch (upFile.getType()) {
                    case "IMAGE" -> {
                        upFile.setUrl(hostName + "/" + imagesFolderPath + newFileName);
                        uploadLocationPath = Paths.get(imagesFolderPath);
                    }
                    case "VIDEO" -> {
                        upFile.setUrl(hostName + "/" + videoFolderPath + newFileName);
                        uploadLocationPath = Paths.get(videoFolderPath);
                    }
                }
            try {
                Files.copy(file.getInputStream(), uploadLocationPath.resolve(newFileName));
                return upFile;
            } catch (Exception e) {
            throw new FileException("File can not be uploaded","StrorageService.java :FileException");
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
