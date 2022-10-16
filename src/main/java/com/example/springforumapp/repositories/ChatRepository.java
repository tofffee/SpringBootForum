package com.example.springforumapp.repositories;

import com.example.springforumapp.models.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Message, Integer>
{

}
