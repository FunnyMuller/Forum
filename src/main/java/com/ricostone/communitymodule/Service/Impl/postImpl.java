package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.discussPostMapper;
import com.ricostone.communitymodule.Entity.Post;
import com.ricostone.communitymodule.Service.post;
import com.ricostone.communitymodule.Util.CommunityNormalValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RicoStone
 * @date 2023/12/8
 */
@Service
public class postImpl implements post {

    @Autowired
    private discussPostMapper discussPostMapper;
    @Override
    public List<Post> getPost(int offset, int limit, int communityId) {
        return discussPostMapper.selectPost(offset, CommunityNormalValue.LIMIT, communityId);
    }
}
