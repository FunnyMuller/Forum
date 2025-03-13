package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.postDetailMapper;
import com.ricostone.communitymodule.Entity.Post;
import com.ricostone.communitymodule.Service.postDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class postDetailImpl implements postDetail {
    @Autowired
    private postDetailMapper postDetailMapper;
    @Override
    public Post getPostDetail(int postId) {
        if(postId == 0){
            return null;
        }
        Post post = postDetailMapper.selectPostById(postId);
        return post;
    }
}
