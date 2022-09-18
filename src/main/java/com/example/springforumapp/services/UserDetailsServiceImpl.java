package com.example.springforumapp.services;

import com.example.springforumapp.models.User;
import com.example.springforumapp.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        else return new UserDetailsImpl(user.get());
    }

    public boolean activateUser(String code)
    {
        Optional<User> user = usersRepository.findUserByActivationCode(code);
        if(user.isEmpty()){
            return false;
        } else{
            user.get().setEnabled(true);
            return true;
        }
    }
}