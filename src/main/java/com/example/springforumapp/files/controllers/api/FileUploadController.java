package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.services.ImagesService;
import com.example.springforumapp.files.services.StorageService;
import com.example.springforumapp.files.util.FileUtil;
import com.example.springforumapp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

        private final StorageService storageService;
        private final ImagesService imagesService;
        private final FileUtil fileUtil;

        @Autowired
    public FileUploadController(StorageService storageService, ImagesService imagesService, FileUtil fileUtil) {
        this.storageService = storageService;
        this.imagesService = imagesService;
            this.fileUtil = fileUtil;
        }


    @PostMapping("/image")
    public ResponseEntity<ResponseApi> uploadImage(@RequestParam("file") MultipartFile image)
    {
        String newImageName = fileUtil.generateRandomImageName(image);
        storageService.store(image, newImageName);
        imagesService.saveImage(null, newImageName);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Image was successfully uploaded"));
    }

}
