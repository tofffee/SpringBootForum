package com.example.springforumapp.files.facades;

import com.example.springforumapp.files.models.domain.UpFile;
import com.example.springforumapp.files.models.dto.UpFileOutDTO;
import com.example.springforumapp.files.services.StorageServiceImpl;
import com.example.springforumapp.files.services.UpFileServiceImpl;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UpFileFacadeImpl implements UpFileFacade{

    private final StorageServiceImpl storageService;
    private final UpFileServiceImpl upFileService;
    private final UsersServiceImpl usersService;
    private final ModelMapper modelMapper;
    @Override
    public UpFileOutDTO saveFile(UserDetailsImpl userDetails, MultipartFile file){
        User user = usersService.findByUsername(userDetails.getUsername());
        UpFile upFile = storageService.store(file);
        upFileService.saveFile(user,upFile);
        return modelMapper.map(upFile, UpFileOutDTO.class);
    }
}
