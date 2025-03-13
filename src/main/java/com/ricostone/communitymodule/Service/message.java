package com.ricostone.communitymodule.Service;

import com.ricostone.communitymodule.Entity.Message;

import java.util.List;

public interface message {
    List<Message> getMessagesByConversationId(String conversationId);
    int insertMessage(Message message);
}
