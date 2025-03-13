package com.ricostone.communitymodule.Service;

import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/12/7
 */
public interface publish {
    Map<String,Object> publishPost(String postTitle, String postContent, String postImageUrl);
}
