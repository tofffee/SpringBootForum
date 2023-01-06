package com.example.springforumapp.users.repositories;

import com.example.springforumapp.users.models.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
