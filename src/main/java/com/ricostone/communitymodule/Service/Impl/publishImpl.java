package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.discussPostMapper;
import com.ricostone.communitymodule.Entity.Post;
import com.ricostone.communitymodule.Service.publish;
import com.ricostone.communitymodule.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/12/7
 */
@Service
public class publishImpl implements publish {
    @Autowired
    private discussPostMapper discussPostMapper;
    @Autowired
    private HostHolder hostHolder;
    @Override
    public Map<String,Object> publishPost(String postTitle, String postContent, String postImageUrl) {
        Map<String,Object> map = new HashMap<>();
        if(postTitle == null){
            map.put("titleMsg","标题不能为空");
            return map;
        }
        if(postContent == null){
            map.put("contentMsg","内容不能为空");
            return map;
        }
        Post post = new Post();
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setPostUserId(hostHolder.getUser().getUserId());
        post.setPostEntityType(0);
        post.setPostEntityShield(0);
        post.setPostEntityStatus(0);
        post.setPostCreateTime(new Date());
        post.setPostImageUrl(postImageUrl);
        post.setCommunityId(1);
        post.setPostUserName(hostHolder.getUser().getUserName());
        discussPostMapper.insertPost(post);
        return map;
    }
}
