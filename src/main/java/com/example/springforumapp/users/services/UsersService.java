package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
import com.example.springforumapp.users.util.exceptions.UserExistsException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
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

    public boolean checkIfUserExistsWithSuchUsername(String username) {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        return user.isPresent();
    }

    public boolean checkIfUserExistsWithSuchEmail(String email){
        Optional<User> user = usersRepository.findUserByEmail(email);
        return user.isPresent();
    }

    public User getUserByEmail(String email){
        Optional<User> user = usersRepository.findUserByEmail(email);
        if (user.isPresent())
            return user.get();
        else throw new UserNotFoundException("User was not getted by Email","user writes email that not exist");
    }

    public User getUserByUsernameOrEmail(String usernameOrEmail) {
        Optional<User> user = usersRepository.findUserByUsername(usernameOrEmail);
        if (user.isPresent())
            return user.get();
        else {
            user = usersRepository.findUserByEmail(usernameOrEmail);
            if (user.isPresent())
                return user.get();
            else return null;
        }
    }

    public void activateUser(User user2, ActivationCodeRequestDTO activationCodeRequestDTO)
    {
        User user = usersRepository.findUserByUsername(user2.getUsername()).get();
        if (user.getEnabled() == true){
            throw new ActivationProfileException("Profile is activated before"," user activated profile before");
        }

        if (user.getActivationCode().equals(activationCodeRequestDTO.getCode())){
            user.setEnabled(true);
        }
        else
            throw new ActivationProfileException("Code is not exists","user sent not existed code");
    }

    public void saveUser(User user){
        if(!usersRepository.findUserByUsername(user.getUsername()).isPresent())
            usersRepository.save(user);
        else throw new UserExistsException();
    }

}