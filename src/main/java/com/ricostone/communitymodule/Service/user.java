package com.ricostone.communitymodule.Service;

import com.ricostone.communitymodule.Entity.User;

public interface user {
   int insertUser(User user);
   User selectUserByName(String userName);
   User selectUserByEmail(String userEmail);
   int updatePassword(String userName, String userPassword);
   User selectUserById(int userId);
}
