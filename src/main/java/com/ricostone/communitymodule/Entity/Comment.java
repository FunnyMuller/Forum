package com.ricostone.communitymodule.Entity;

import java.util.Date;
import java.util.List;

/**
 * @author RicoStone
 * @date 2023/11/26
 */
public class Comment {
    // 社区 Id
    private int communityId;
    // 评论的 Id
    private int commentId;
    // 评论的帖子 Id
    private int commentPostId;
    // 评论的内容
    private String commentContent;
    // 评论的用户 Id
    private int commentUserId;
    // 评论者的头像地址
    private String commentImageUrl;
    // 评论的创建时间
    private Date commentCreateTime;
    // 评论的实体类型
    private int commentEntityType;
    // 评论的目标名字
    private String commentTargetName;
    // 评论的用户名
    private String commentUsername;
    // 评论的目标 Id
    private List<Comment> childComment;
    private int commentTargetId;

    public int getCommentTargetId() {
        return commentTargetId;
    }

    public void setCommentTargetId(int commentTargetId) {
        this.commentTargetId = commentTargetId;
    }

    public List<Comment> getChildComment() {
        return childComment;
    }

    public void setChildComment(List<Comment> childComment) {
        this.childComment = childComment;
    }

    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getCommentTargetName() {
        return commentTargetName;
    }

    public void setCommentTargetName(String commentTargetName) {
        this.commentTargetName = commentTargetName;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentPostId() {
        return commentPostId;
    }

    public void setCommentPostId(int commentPostId) {
        this.commentPostId = commentPostId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentImageUrl() {
        return commentImageUrl;
    }

    public void setCommentImageUrl(String commentImageUrl) {
        this.commentImageUrl = commentImageUrl;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public int getCommentEntityType() {
        return commentEntityType;
    }

    public void setCommentEntityType(int commentEntityType) {
        this.commentEntityType = commentEntityType;
    }
}
