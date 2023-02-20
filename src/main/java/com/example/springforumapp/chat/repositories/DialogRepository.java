package com.example.springforumapp.chat.repositories;

import com.example.springforumapp.chat.models.domain.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
//    @Query("select d from Dialog d join User u where d.us")
//    public Optional<Dialog> findDialogByUsersId(long user1Id, long user2Id);
}
