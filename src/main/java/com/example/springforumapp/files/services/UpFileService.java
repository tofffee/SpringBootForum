package com.example.springforumapp.files.services;

import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.users.models.domain.User;

import java.util.List;

public interface UpFileService {
    public UpFile findFileById(long id) ;
    public List<UpFile> findFilesByListIds(List<Integer> filesIdList);
    public void saveFile(User user, UpFile upFile);
}
