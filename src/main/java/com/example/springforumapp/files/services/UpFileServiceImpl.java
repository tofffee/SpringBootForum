package com.example.springforumapp.files.services;


import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.repositories.UpFilesRepository;
import com.example.springforumapp.files.util.exceptions.FileException;
import com.example.springforumapp.users.models.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UpFileServiceImpl implements UpFileService {
    private final UpFilesRepository upFilesRepository;

    @Override
    public UpFile findFileById(long id) throws FileException{
        Optional<UpFile> file = upFilesRepository.findById(id);
        if (file.isEmpty())
            throw new FileException("Such file does not exist", "UpFileService.java: FileException");
        return file.get();
    }

    @Override
    public List<UpFile> findFilesByListIds(List<Integer> filesIdList) throws FileException {
        List<UpFile> upfiles = new ArrayList<>();
        filesIdList.forEach(fileId -> upfiles.add(findFileById(fileId)));
        return upfiles;
    }

    @Override
    @Transactional
    public void saveFile(User user, UpFile upFile) {
        upFile.setUser(user);
        upFile.setCreatedAt(LocalDate.now());
        upFilesRepository.save(upFile);
    }

}
