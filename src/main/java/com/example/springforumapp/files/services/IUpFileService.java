package com.example.springforumapp.files.services;

import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.users.models.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface IUpFileService {
    public UpFile findFileById(long id) ;
    public void saveFile(User user, UpFile upFile);
}
