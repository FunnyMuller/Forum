package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.Impl.registerImpl;
import com.ricostone.communitymodule.Util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/11/26
 */
@Controller
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class registerController {
    private static final Logger logger = LoggerFactory.getLogger(registerController.class);
    @Autowired
    private registerImpl registerImpl;

    /**
     * 跳转到注册页面
     * @return 注册页面
     */
    @RequestMapping(path="/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/register";
    }
    @RequestMapping(path="/register",method = RequestMethod.POST)
    public String register(User user, Model model) throws Exception {
        Map<String,Object> map =  registerImpl.register(user);
        // TODO: 将错误信息渲染到页面上
        if(map == null || map.isEmpty()){
            return "/index";
        }else{
            model.addAttribute("userNameMsg",map.get("userNameMsg"));
            model.addAttribute("userPasswordMsg",map.get("userPasswordMsg"));
            model.addAttribute("userEmailMsg",map.get("userEmailMsg"));
            return "/register";
        }
    }
}
