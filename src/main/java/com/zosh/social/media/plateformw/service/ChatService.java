package com.zosh.social.media.plateformw.service;

import com.zosh.social.media.plateformw.models.Chat;
import com.zosh.social.media.plateformw.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser , User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat (Integer userId);




}
