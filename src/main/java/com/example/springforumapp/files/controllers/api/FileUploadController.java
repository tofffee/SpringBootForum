package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.files.services.ImagesService;
import com.example.springforumapp.files.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

        private final StorageService storageService;
        private final ImagesService imagesService;

        @Autowired
    public FileUploadController(StorageService storageService, ImagesService imagesService) {
        this.storageService = storageService;
        this.imagesService = imagesService;
    }


    @PostMapping()
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String newFileName = StringUtils.cleanPath(UUID.randomUUID().toString().substring(0, 8) +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        storageService.store(file, newFileName);
        imagesService.saveImage(file, newFileName);

        return ResponseEntity.ok("successPage");
    }

//    @ExceptionHandler
//    private ResponseEntity<?> handleException(FileNotSavedException fileNotSavedException)
//    {
//        ResponseError imageResponseError = new ResponseError(
//                fileNotSavedException.getMessage(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(imageResponseError, HttpStatus.BAD_REQUEST);
//    }



}
