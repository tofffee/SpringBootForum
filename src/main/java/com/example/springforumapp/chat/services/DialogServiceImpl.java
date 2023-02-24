package com.example.springforumapp.chat.services;

import com.example.springforumapp.chat.models.domain.Dialog;
import com.example.springforumapp.chat.models.dto.DialogOutDTO;
import com.example.springforumapp.chat.repositories.DialogRepository;
import com.example.springforumapp.chat.util.exceptions.DialogNotFoundException;
import com.example.springforumapp.users.models.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class DialogServiceImpl implements DialogService{

    private final DialogRepository dialogRepository;
    private final ModelMapper modelMapper;

    @Override
    public Dialog findById(long id) throws DialogNotFoundException{
        Optional<Dialog> dialog = dialogRepository.findById(id);
        if(dialog.isEmpty())
            throw new DialogNotFoundException("Such dialog does not exist", "DialogServiceImpl.java: DialogNotFoundException ");
        return dialog.get();
    }


    @Transactional
    @Override
    public DialogOutDTO findByUsers(User user1, User user2) {
        for (Dialog dialog2 : user2.getDialogs()) {
            if (user1.getDialogs().contains(dialog2))
                return modelMapper.map(dialog2, DialogOutDTO.class);
        }
            Dialog dialog = new Dialog();
            dialog.setCreatedAt(LocalDate.now());
            dialog.addUsers(Set.of(user1, user2));
            dialogRepository.save(dialog);
            return modelMapper.map(dialog, DialogOutDTO.class);
    }
}
