package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.MessageListInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageListInfoMapper {
    List<MessageListInfo> selectMessageByToId(int toUserId);
    int updateMessageContent(String conversationId, String content);
    int insertMessage(MessageListInfo messageListInfo);
}
