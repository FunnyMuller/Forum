package com.ricostone.communitymodule;

import com.ricostone.communitymodule.DAO.userMapper;
import com.ricostone.communitymodule.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author RicoStone
 * @date 2023/11/29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    private userMapper userMapper;
    @Test
    public void testSelect(){
        User user = userMapper.selectUserByName("RicoStone");
        System.out.println(user.getUserEmail());
    }
    @Test
    public void testSelectById(){
        User user = userMapper.selectUserById(1);
        System.out.println(user.getUserName());
    }
}
