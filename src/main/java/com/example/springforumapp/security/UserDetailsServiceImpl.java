package com.example.springforumapp.security;

import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        else return new UserDetailsImpl(user.get());
    }
}
