package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author RicoStone
 * @date 2023/12/7
 */
@Mapper
public interface discussPostMapper {
    List<Post> selectPost(int offset, int limit, int communityId);
    int insertPost(Post post);
}
