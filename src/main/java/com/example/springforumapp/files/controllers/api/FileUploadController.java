package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.files.services.UpFileService;
import com.example.springforumapp.files.services.StorageService;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {
        private final StorageService storageService;
        private final UpFileService upFileService;
        private final UsersServiceImpl usersServiceImpl;
        private final ModelMapper modelMapper;

        @PostMapping()
        public ResponseEntity<ResponseApi> uploadImage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestParam("file") MultipartFile file) {
            UpFile upFile = storageService.store(file);
            upFileService.saveFile(usersServiceImpl.findById(userDetails.getUser().getId()),upFile);
            UpFileOutDTO dto = modelMapper.map(upFile, UpFileOutDTO.class);
            return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
        }
}
