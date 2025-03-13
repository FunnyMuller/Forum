package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.Entity.Message;
import com.ricostone.communitymodule.Service.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ricostone.communitymodule.DAO.messageMapper;
import java.util.List;

@Service
public class messageImpl implements message {
    @Autowired
    private messageMapper messageMapper;
    @Override
    public List<Message> getMessagesByConversationId(String conversationId) {
        return messageMapper.getMessagesByConversationId(conversationId);
    }
    @Override
    public int insertMessage(Message message) {
        return messageMapper.insertMessage(message);
    }
}
