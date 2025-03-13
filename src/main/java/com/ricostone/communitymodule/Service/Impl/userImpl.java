package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ricostone.communitymodule.DAO.userMapper;
@Service
public class userImpl implements user {
   @Autowired
   private userMapper userMapper;
   @Override
   public int insertUser(User user) {
	  return userMapper.insertUser(user);
   }

   @Override
   public User selectUserByName(String userName) {
	  return userMapper.selectUserByName(userName);
   }

   @Override
   public User selectUserByEmail(String userEmail) {
	  return userMapper.selectUserByEmail(userEmail);
   }

   @Override
   public int updatePassword(String userName, String userPassword) {
	  return userMapper.updatePassword(userName,userPassword);
   }

   @Override
   public User selectUserById(int userId) {
	  return userMapper.selectUserById(userId);
   }
}
