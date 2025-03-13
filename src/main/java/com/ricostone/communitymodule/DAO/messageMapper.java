package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface messageMapper {
    /**
     * 根据会话id得到整个私信内容
     * @param conversationId 会话id
     * @return 私信详细内容
     */
    List<Message> getMessagesByConversationId(String conversationId);

    /**
     * 插入会话内容
     * @param message 会话的所有信息
     * @return int, 0 代表失败， 其他代表成功
     */
    int insertMessage(Message message);
}
