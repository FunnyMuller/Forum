package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.MessageListInfoMapper;
import com.ricostone.communitymodule.Entity.MessageListInfo;
import com.ricostone.communitymodule.Service.messageListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageListInfoImpl implements messageListInfo {
    @Autowired
    private MessageListInfoMapper messageListInfoMapper;

    @Override
    public List<MessageListInfo> selectMessageByToId(int toId) {
        // TODO: 做一个查询，查询最大的id值，然后去判断toid是否溢出，可能没必要
        /*
        int maxId = userMapper.selectMaxId();
        if (toId > maxId) {
            return null;
         */
        return messageListInfoMapper.selectMessageByToId(toId);
    }
    @Override
    public int updateMessageContent(String content, String conversationId) {
        return messageListInfoMapper.updateMessageContent(content, conversationId);
    }

    @Override
    public int insertMessage(MessageListInfo messageListInfo) {
        return messageListInfoMapper.insertMessage(messageListInfo);
    }
}
