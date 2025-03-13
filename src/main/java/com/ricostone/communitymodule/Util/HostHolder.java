package com.ricostone.communitymodule.Util;

import com.ricostone.communitymodule.Entity.User;
import org.springframework.stereotype.Component;

/**
 * @author RicoStone
 * @date 2023/12/6
 */

/**
 * 持有用户信息,用于代替session对象.
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
