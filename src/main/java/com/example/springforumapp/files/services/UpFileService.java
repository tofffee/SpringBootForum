package com.example.springforumapp.files.services;


import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.repositories.UpFilesRepository;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UpFileService {
    private final UpFilesRepository upFilesRepository;

    @Autowired
    public UpFileService(UpFilesRepository upFilesRepository) {
        this.upFilesRepository = upFilesRepository;
    }

    public UpFile findFileById(int id) {
        Optional<UpFile> file = upFilesRepository.findById(id);
        if (file.isPresent())
            return file.get();
        else throw new FileException("Such file does not exist", "ImagesService.java: FileException");
    }

    public void saveFile(User user, UpFile upFile) {
        upFile.setUser(user);
        upFilesRepository.save(upFile);
    }
//    public Image findImageById(int id){
//        Optional<Image> image = imagesRepository.findById(id);
//        if(image.isPresent())
//            return image.get();
//        else throw new FileException("Such image does not exist","ImagesService.java: FileException");
//    }
//    public Image saveImage(User user, String newImageName)
//    {
//        Image image = new Image();
//        image.setUser(user);
//        image.setUrl(hostName + "/upload/images/" + newImageName);
//        image.setName(newImageName);
//        imagesRepository.save(image);
//        return image;
//    }
//
//    public void deleteImage(int id)
//    {
//        Optional<Image> image = imagesRepository.findById(id);
//        if (image.isPresent())
//            imagesRepository.delete(image.get());
//        else throw new FileException("Such image does not exist","ImagesService.java: FileException");
//      }
}
