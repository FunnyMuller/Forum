package com.ricostone.communitymodule.Entity;

import java.util.Date;

/**
 * @author RicoStone
 * @date 2023/11/26
 */
public class Post {
    private int communityId;
    private int postId;
    private String postUserName;
    private String postTitle;
    private String postContent;
    private int postUserId;
    private int postEntityType;
    private int postEntityShield;
    private int postEntityStatus;
    private Date postCreateTime;
    private String postImageUrl;

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(int postUserId) {
        this.postUserId = postUserId;
    }

    public int getPostEntityType() {
        return postEntityType;
    }

    public void setPostEntityType(int postEntityType) {
        this.postEntityType = postEntityType;
    }

    public int getPostEntityShield() {
        return postEntityShield;
    }

    public void setPostEntityShield(int postEntityShield) {
        this.postEntityShield = postEntityShield;
    }

    public int getPostEntityStatus() {
        return postEntityStatus;
    }

    public void setPostEntityStatus(int postEntityStatus) {
        this.postEntityStatus = postEntityStatus;
    }

    public Date getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(Date postCreateTime) {
        this.postCreateTime = postCreateTime;
    }
    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
