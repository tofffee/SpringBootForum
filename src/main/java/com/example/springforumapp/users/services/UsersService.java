package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsersService implements UserDetailsService, IUsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        else return new UserDetailsImpl(user.get());
    }

    public User findByUsername(String username) {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        return user.orElse(null);
    }

    public User findByEmail(String email){
        Optional<User> user =  usersRepository.findUserByEmail(email);
        return user.orElse(null);
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        Optional<User> user = usersRepository.findUserByUsername(usernameOrEmail);
        if (user.isPresent())
            return user.get();
        else
            return usersRepository.findUserByEmail(usernameOrEmail).orElse(null);
    }

    public void activateUser(UserDetailsImpl userDetailsImpl, ActivationCodeRequestDTO activationCodeRequestDTO)
    {
        User user = usersRepository.findUserByUsername(userDetailsImpl.getUsername()).get();
        if (user.getEnabled()){
            throw new ActivationProfileException("Your account is already activated","UsersService.java: ActivationProfileException");
        }

        if (user.getActivationCode().equals(activationCodeRequestDTO.getCode())){
            user.setEnabled(true);
            user.setActivationCode(null);
        } else throw new ActivationProfileException("Your activation code is not correct","UsersService.java: ActivationProfileException");
    }

    public void saveUser(User user){
        usersRepository.save(user);
    }
}