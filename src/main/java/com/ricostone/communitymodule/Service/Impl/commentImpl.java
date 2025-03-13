package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.Entity.Comment;
import com.ricostone.communitymodule.Service.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ricostone.communitymodule.DAO.commentMapper;
import java.util.List;

@Service
public class commentImpl implements comment {
    @Autowired
    private commentMapper commentMapper;
    @Override
    public List<Comment> getComments(int commentEntityType, int commentPostId, String commentTargetName, int commentTargetId) {
        return commentMapper.getCommentList(commentEntityType,commentPostId,commentTargetName, commentTargetId);
    }

    @Override
    public int getCommentCount(int commentPostId) {
        return commentMapper.getCommentCount(commentPostId);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentMapper.getCommentById(commentId);
    }
}
