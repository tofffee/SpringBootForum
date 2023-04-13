package com.example.springforumapp.files.controllers.api;


import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.files.facades.UpFileFacade;
import com.example.springforumapp.files.facades.UpFileFacadeImpl;
import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.files.services.UpFileServiceImpl;
import com.example.springforumapp.files.services.StorageServiceImpl;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@Slf4j
@RequiredArgsConstructor
public class FileUploadController {
        private final UpFileFacade upFileFacade;

        @PostMapping()
        public ResponseEntity<ResponseApi> uploadFile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestParam("file") MultipartFile file) {
            UpFileOutDTO dto = upFileFacade.saveFile(userDetails, file);
            return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, dto));
        }
}
