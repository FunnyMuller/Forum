package com.ricostone.communitymodule.Service;

import com.ricostone.communitymodule.Entity.Comment;

import java.util.List;

public interface comment {
    List<Comment> getComments(int commentEntityType, int commentPostId, String commentTargetName, int commentId);
    int getCommentCount(int commentPostId);
    Comment getCommentById(int commentId);
}
