package com.example.springforumapp.users.repositories;

import com.example.springforumapp.users.models.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Ref;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefrtoken(String refrtroken);
    Optional<RefreshToken> findByUserId(long userId);
}
