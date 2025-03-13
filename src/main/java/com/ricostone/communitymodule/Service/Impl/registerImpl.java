package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.userMapper;
import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.register;
import com.ricostone.communitymodule.Util.AESEncryptUtil;
import com.ricostone.communitymodule.Util.CommunityNormalValue;
import com.ricostone.communitymodule.Util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/11/27
 */
@Service
public class registerImpl implements register {
    @Autowired
    private userImpl userImpl;
    /**
     * 用户注册方法
     * @param user 用户对象
     * @return
     */
    public Map<String,Object> register(User user) throws Exception {
        Map<String,Object> map = new HashMap<>();
        /* 在这里先进行空值判断，主要是对 `用户姓名`, `用户密码`, `用户邮箱`, `用户头像` 进行判断
           另外还需要将获取到的姓名和邮箱进行判断是否存在，如果存在则返回错误信息
         */
        if(user == null) throw new IllegalArgumentException("参数不能为空");
        // TODO: 头像上传
//        if(StringUtils.isBlank(user.getUserImageUrl())){
//            map.put("userImageUrlMsg","头像不能为空");
//            return map;
//        }
        User selectUser = userImpl.selectUserByName(user.getUserName());
        if(selectUser != null){
            map.put("userNameMsg","用户名已经被注册！");
            return map;
        }
        selectUser = userImpl.selectUserByEmail(user.getUserEmail());
        if(selectUser != null){
            map.put("userEmailMsg","邮箱已经被注册！");
            return map;
        }
        user.setUserCreateTime(new Date());
        /*
        在这里主要是加密，防止用户的密码出现过于简单的情况，出现安全问题，采用的是 ARS 加密算法
         */
        user.setUserPassword(AESEncryptUtil.encrypt(user.getUserPassword(), CommunityNormalValue.AESKEY));
        user.setUserEmail(user.getUserEmail());
        user.setUserImageUrl("https://picsum.photos/id/" + CommunityUtil.getRandomInt(1,100) +"/200/300");
        user.setUserDestroy(1);
        userImpl.insertUser(user);
        return map;
    }
}
