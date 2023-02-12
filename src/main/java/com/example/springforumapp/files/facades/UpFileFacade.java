package com.example.springforumapp.files.facades;

import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.security.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

public interface UpFileFacade {
    public UpFileOutDTO saveFile(UserDetailsImpl userDetails, MultipartFile file);
}
