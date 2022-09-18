package com.example.springforumapp.repositories;

import com.example.springforumapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByActivationCode(String code);
}
