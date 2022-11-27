package com.example.springforumapp.files.services;


import com.example.springforumapp.files.models.domain.Image;
import com.example.springforumapp.files.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class ImagesService {

    @Value("${server.hostname}")
    String hostName;
    private final ImagesRepository imagesRepository;

    @Autowired
    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }



    public void saveImage(MultipartFile file, String newFileName)
    {

        Image image = new Image();
        image.setUrl(hostName + "/upload/images/" + newFileName);
        imagesRepository.save(image);
    }

}
