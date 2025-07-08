package com.zosh.social.media.plateformw.repository;

import com.zosh.social.media.plateformw.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

     public List<Message> findByChatId(Integer chatId);

}
