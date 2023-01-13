package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.files.services.UpFileService;
import com.example.springforumapp.files.services.StorageService;
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
        private final UpFileService upFileService;
        private final UsersService usersService;
        private final ModelMapper modelMapper;

        @Autowired
        public FileUploadController(StorageService storageService, UpFileService upFileService, UsersService usersService, ModelMapper modelMapper) {
            this.storageService = storageService;
            this.upFileService = upFileService;
            this.usersService = usersService;
            this.modelMapper = modelMapper;
        }


        @PostMapping()
        public ResponseEntity<ResponseApi> uploadImage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestParam("file") MultipartFile file) {

            UpFile upFile = storageService.store(file);
            upFileService.saveFile(usersService.findById(userDetails.getUser().getId()),upFile);
            UpFileOutDTO dto = modelMapper.map(upFile, UpFileOutDTO.class);
            return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), dto));
        }

//        @DeleteMapping ("/image/{id}")
//        public ResponseEntity<ResponseApi> deleteImage(@PathVariable("id") int id) {
//            Image image = fileService.findImageById(id);
//            storageService.delete(image.getName());
//            fileService.deleteImage(id);
//            return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Image was successfully deleted"));
//        }


}
