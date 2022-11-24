package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        else return new UserDetailsImpl(user.get());
    }

    public boolean checkUserExists(String username) {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        return user.isPresent();
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

  //  public void createGuestUser(User user)
//    {
//        usersRepository.save(user);
//    }
}