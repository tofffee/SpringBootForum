package com.example.springforumapp.files.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class FileUtil {

    public String generateRandomImageName(MultipartFile file){
       return StringUtils.cleanPath(UUID.randomUUID().toString().substring(0, 8) +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
    }

    public String getFileExtension(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }
        return extension;
    }

}
