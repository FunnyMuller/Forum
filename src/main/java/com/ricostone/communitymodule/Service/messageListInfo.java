package com.ricostone.communitymodule.Service;

import com.ricostone.communitymodule.Entity.MessageListInfo;

import java.util.List;

public interface messageListInfo {
    List<MessageListInfo> selectMessageByToId(int toId);
    int updateMessageContent(String content, String conversationId);
    int insertMessage(MessageListInfo messageListInfo);
}
