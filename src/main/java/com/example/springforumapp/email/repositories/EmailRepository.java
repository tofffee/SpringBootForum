package com.example.springforumapp.email.repositories;

import com.example.springforumapp.email.models.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository  extends JpaRepository<Email, Integer> {
}
