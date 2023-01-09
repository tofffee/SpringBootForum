package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.domain.Image;
import com.example.springforumapp.files.models.dto.ImageOutDTO;
import com.example.springforumapp.files.services.ImagesService;
import com.example.springforumapp.files.services.StorageService;
import com.example.springforumapp.files.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

        private final StorageService storageService;
        private final ImagesService imagesService;
        private final FileUtil fileUtil;
        private final ModelMapper modelMapper;

        @Autowired
    public FileUploadController(StorageService storageService, ImagesService imagesService, FileUtil fileUtil, ModelMapper modelMapper) {
        this.storageService = storageService;
        this.imagesService = imagesService;
            this.fileUtil = fileUtil;
            this.modelMapper = modelMapper;
        }


    @PostMapping("/image")
    public ResponseEntity<ResponseApi> uploadImage(@RequestParam("file") MultipartFile image) {
        String newImageName = fileUtil.generateRandomImageName(image);
        storageService.store(image, newImageName);
        Image savedImage = imagesService.saveImage(null, newImageName);
        ImageOutDTO imageOutDTO = modelMapper.map(savedImage, ImageOutDTO.class);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), imageOutDTO));
    }

    @DeleteMapping ("/image/{id}")
    public ResponseEntity<ResponseApi> deleteImage(@PathVariable("id") int id) {
        Image image = imagesService.findImageById(id);
        storageService.delete(image.getName());
        imagesService.deleteImage(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Image was successfully deleted"));
    }


}
