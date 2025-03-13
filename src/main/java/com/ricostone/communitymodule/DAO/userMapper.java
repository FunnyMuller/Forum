package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author RicoStone
 * @date 2023/11/26
 */
@Mapper
public interface userMapper {
    int insertUser(User user);
    User selectUserByName(String userName);
    User selectUserByEmail(String userEmail);
    int updatePassword(String userName, String userPassword);
    User selectUserById(int userId);
}
