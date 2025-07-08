package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Chat;
import com.zosh.social.media.plateformw.models.Message;
import com.zosh.social.media.plateformw.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user , Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
