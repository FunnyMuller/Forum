package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface postDetailMapper {
    Post selectPostById(int postId);
}
