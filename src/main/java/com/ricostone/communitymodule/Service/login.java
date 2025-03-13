package com.ricostone.communitymodule.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/11/30
 */
public interface login {
    public Map<String,Object> login(String userName, String userPassword, int expiredSeconds) throws Exception;
    public Map<String,Object> verifyIdentity(String username, String userEmail);
    public Map<String,Object> resetPassword(String username, String password, String confirmPassword) throws Exception;
}