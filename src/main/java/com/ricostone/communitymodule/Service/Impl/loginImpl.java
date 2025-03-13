package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.loginTicketMapper;
import com.ricostone.communitymodule.DAO.userMapper;
import com.ricostone.communitymodule.Entity.LoginTicket;
import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.login;
import com.ricostone.communitymodule.Util.AESEncryptUtil;
import com.ricostone.communitymodule.Util.CommunityNormalValue;
import com.ricostone.communitymodule.Util.CommunityUtil;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/11/30
 */
@Service
public class loginImpl implements login {
    @Autowired
    private userImpl userImpl;
    @Autowired
    private loginTicketMapper loginTicketMapper;
    /**
     * 登录
     * @param userName 用户姓名
     * @param userPassword 用户密码
     * @return 错误信息哈希表
     * @throws Exception
     */
    @Override
    public Map<String,Object> login(String userName, String userPassword, int expiredSeconds) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(userName)){
            map.put("userNameMsg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(userPassword)){
            map.put("userPasswordMsg","密码不能为空");
            return map;
        }
        User user = userImpl.selectUserByName(userName);
        if(user == null){
            map.put("userNameMsg","用户名不存在");
            return map;
        }
        String password = AESEncryptUtil.decrypt(user.getUserPassword(), CommunityNormalValue.AESKEY);
        if(!password.equals(userPassword)){
            map.put("userPasswordMsg","密码输入错误");
            return map;
        }
        // 生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getUserId());
        loginTicket.setTicket(CommunityUtil.getUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
        loginTicketMapper.insertLoginTicket(loginTicket);

        map.put("ticket", loginTicket.getTicket());
        return map;
    }

    /**
     * 身份验证
     * @param userName 用户姓名
     * @param userEmail 用户邮箱
     * @return 错误信息哈希表
     */
    @Override
    public Map<String, Object> verifyIdentity(String userName, String userEmail) {
        Map<String,Object> map = new HashMap<>();
        User user = userImpl.selectUserByName(userName);
        if(user == null){
            map.put("userNameMsg","用户名不存在");
            return map;
        }
        if(!user.getUserEmail().equals(userEmail)){
            map.put("userEmailMsg","邮箱输入错误");
            return map;
        }
        map.put("userName",userName);
        return map;
    }

    /**
     * 重置密码
     * @param password 重置后的密码
     * @param confirmPassword 确认密码
     * @return 错误信息哈希表
     */
    public Map<String,Object> resetPassword(String userName, String password, String confirmPassword) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(password)){
            map.put("passwordMsg","密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(confirmPassword)){
            map.put("confirmPasswordMsg","确认密码不能为空");
            return map;
        }
        if(!password.equals(confirmPassword)){
            map.put("confirmPasswordMsg","两次密码输入不一致");
            return map;
        }
        password = AESEncryptUtil.encrypt(password, CommunityNormalValue.AESKEY);
        userImpl.updatePassword(userName,password);
        return map;
    }

    /**
     * 退出登录
     * @param ticket
     */
    public int logout(String ticket) {
        return loginTicketMapper.updateStatus(ticket, 1);
    }

    /**
     * 根据ticket查询登录凭证
     * @param ticket
     * @return
     */
    public LoginTicket findLoginTicket(String ticket) {
        return loginTicketMapper.selectByTicket(ticket);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User findUserById(int id) {
        return userImpl.selectUserById(id);
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User findUserByName(String username) {
        return userImpl.selectUserByName(username);
    }
}
