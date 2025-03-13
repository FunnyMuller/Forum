package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface commentMapper {
    // 参数：
    // commentEntityType：评论的实体类型
    // commentId：评论 帖子/评论 Id
    // commentTargetName：评论的目标名字
    List<Comment> getCommentList(int commentEntityType, int commentPostId, String commentTargetName, int commentTargetId);
    int getCommentCount(int commentPostId);
    int insertComment(Comment comment);
    Comment getCommentById(int commentTargetId);
}
