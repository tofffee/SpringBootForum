package com.example.springforumapp.repositories;

import com.example.springforumapp.models.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Integer>
{

}
