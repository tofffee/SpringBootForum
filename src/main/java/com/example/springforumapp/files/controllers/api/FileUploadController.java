package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.domain.Image;
import com.example.springforumapp.files.models.dto.ImageOutDTO;
import com.example.springforumapp.files.services.ImagesService;
import com.example.springforumapp.files.services.StorageService;
import com.example.springforumapp.files.util.FileUtil;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

        private final StorageService storageService;
        private final ImagesService imagesService;
        private final UsersService usersService;
        private final FileUtil fileUtil;
        private final ModelMapper modelMapper;


        private final long MAX_IMAGE_SIZE = 20*1024*1024;
        @Autowired
        public FileUploadController(StorageService storageService, ImagesService imagesService, UsersService usersService, FileUtil fileUtil, ModelMapper modelMapper) {
            this.storageService = storageService;
            this.imagesService = imagesService;
            this.usersService = usersService;
            this.fileUtil = fileUtil;
            this.modelMapper = modelMapper;
        }


        @PostMapping("/image")
        public ResponseEntity<ResponseApi> uploadImage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestParam("file") MultipartFile image) {
//            if(image.getSize()>MAX_IMAGE_SIZE) //check for size
//                throw new FileException("Image is very big","FileUploadController.java: FileException");
//
//            if(!image.getContentType().equals(MimeTypeUtils.IMAGE_JPEG.toString()) && !image.getContentType().equals(MimeTypeUtils.IMAGE_PNG.toString())){
//                throw new FileException("This is not image","FileUploadController.java: FileException");
//            }
//
//            String fileExtension = fileUtil.getFileExtension(image.getOriginalFilename());
//            if(!fileExtension.equals("jpeg") && !fileExtension.equals("jpg") && !fileExtension.equals("png")){
//                throw new FileException("This is not image","FileUploadController.java: FileException");
//            }

            String storedImageName = storageService.store(image);
            Image savedImage = imagesService.saveImage(usersService.findById(userDetails.getUser().getId()), storedImageName);
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
