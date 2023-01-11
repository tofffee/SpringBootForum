package com.example.springforumapp.files.services;


import com.example.springforumapp.files.models.domain.Image;
import com.example.springforumapp.files.repositories.ImagesRepository;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.publications.models.domain.Publication;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;
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

    public Image findImageById(int id){
        Optional<Image> image = imagesRepository.findById(id);
        if(image.isPresent())
            return image.get();
        else throw new FileException("Such image does not exist","ImagesService.java: FileException");
    }
    public Image saveImage(User user, String newImageName)
    {
        Image image = new Image();
        image.setUser(user);
        image.setUrl(hostName + "/upload/images/" + newImageName);
        image.setName(newImageName);
        imagesRepository.save(image);
        return image;
    }

    public void deleteImage(int id)
    {
        Optional<Image> image = imagesRepository.findById(id);
        if (image.isPresent())
            imagesRepository.delete(image.get());
        else throw new FileException("Such image does not exist","ImagesService.java: FileException");
    }
}
