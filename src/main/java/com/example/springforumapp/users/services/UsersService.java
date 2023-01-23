package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.Role;
import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.RolesRepository;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersService implements IUsersService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public User findById(long id) {
        Optional<User> user =  usersRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }

    public User findByUsername(String username) {
        Optional<User> user =  usersRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }

    public User findByEmail(String email){
        Optional<User> user =  usersRepository.findByEmail(email);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        Optional<User> user = usersRepository.findByUsername(usernameOrEmail);
        if(user.isEmpty())
            throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
        return user.get();
    }

    public boolean existsByUsername(String username) {
        Optional<User> user = usersRepository.findByUsername(username);
        return user.isPresent();
    }

    public boolean existsByEmail(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        return user.isPresent();
    }

    @Transactional
    public void activateUser(UserDetailsImpl userDetailsImpl, ActivationCodeRequestDTO activationCodeRequestDTO)
    {
        User user = usersRepository.findByUsername(userDetailsImpl.getUsername()).get();
        if (user.getEnabled()){
            throw new ActivationProfileException("Your account is already activated","UsersService.java: ActivationProfileException");
        }

        if (user.getActivationCode().equals(activationCodeRequestDTO.getCode())){
            user.setEnabled(true);
            user.setActivationCode(null);
        } else throw new ActivationProfileException("Your activation code is not correct","UsersService.java: ActivationProfileException");
    }


    @Transactional
    public void addUser(User user){
        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        usersRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");

        usersRepository.deleteById(id);
    }

    @Transactional
    public void grantAdminRole(long id){
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");

        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        roles.add(rolesRepository.findByName("ROLE_ADMIN"));
        user.get().setRoles(roles);
        usersRepository.save(user.get());
    }

    @Transactional
    public void ungrantAdminRole(long id){
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");

        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        user.get().setRoles(roles);
        usersRepository.save(user.get());
    }

}