package com.example.springforumapp.users.services;

import com.example.springforumapp.users.models.domain.Role;
import com.example.springforumapp.users.models.dto.ActivationCodeRequestDTO;
import com.example.springforumapp.users.models.domain.User;
import com.example.springforumapp.users.repositories.RolesRepository;
import com.example.springforumapp.users.repositories.UsersRepository;
import com.example.springforumapp.security.UserDetailsImpl;
import com.example.springforumapp.users.util.exceptions.ActivationProfileException;
import com.example.springforumapp.users.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersService implements UserDetailsService, IUsersService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  usersRepository.findUserByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        else return new UserDetailsImpl(user.get());
    }

    public User findBId(int id) {
        Optional<User> user =  usersRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else throw new UserNotFoundException("User was not found", "UsersService.java: UserNotFoundException");
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


    public void addUser(User user){
        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = usersRepository.findById(id);
        if(user.isPresent()){
           usersRepository.deleteById(id);
        } else throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");
    }

    public void addAdmin(User user){
        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepository.findByName("ROLE_USER"));
        roles.add(rolesRepository.findByName("ROLE_ADMIN"));
        user.setRoles(roles);
        usersRepository.save(user);
    }

    public void grantAdminRole(int id){
        Optional<User> user = usersRepository.findById(id);
        if(user.isPresent()){
            List<Role> roles = new ArrayList<>();
            roles.add(rolesRepository.findByName("ROLE_USER"));
            roles.add(rolesRepository.findByName("ROLE_ADMIN"));
            user.get().setRoles(roles);
            usersRepository.save(user.get());
        } else throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");
    }

    public void ungrantAdminRole(int id){
        Optional<User> user = usersRepository.findById(id);
        if(user.isPresent()){
            List<Role> roles = new ArrayList<>();
            roles.add(rolesRepository.findByName("ROLE_USER"));
            user.get().setRoles(roles);
            usersRepository.save(user.get());
        } else throw new UserNotFoundException("Such user is not found","UsersService.java : UserNotFoundException");
    }

}