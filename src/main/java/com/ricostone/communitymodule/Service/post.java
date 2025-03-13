package com.ricostone.communitymodule.Service;

import com.ricostone.communitymodule.Entity.Post;

import java.util.List;

/**
 * @author RicoStone
 * @date 2023/12/8
 */
public interface post {
    List<Post> getPost(int offset, int limit, int communityId);
}
