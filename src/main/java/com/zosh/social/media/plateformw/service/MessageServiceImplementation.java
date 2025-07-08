package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Chat;
import com.zosh.social.media.plateformw.models.Message;
import com.zosh.social.media.plateformw.models.User;
import com.zosh.social.media.plateformw.repository.ChatRepository;
import com.zosh.social.media.plateformw.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;



    @Override
    public Message createMessage(User user, Integer chatId,Message req ) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        Message message = new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

      Message savedMessage =   messageRepository.save(message);

      chat.getMessages().add(savedMessage);
      chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);
    }
}
