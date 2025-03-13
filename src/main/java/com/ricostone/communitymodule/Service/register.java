package com.ricostone.communitymodule.Service;

import com.ricostone.communitymodule.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/11/27
 */
public interface register {
    public Map<String,Object> register(User user) throws Exception;
}
