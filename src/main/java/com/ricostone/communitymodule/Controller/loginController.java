package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Service.Impl.loginImpl;
import com.ricostone.communitymodule.Util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/11/30
 */
@Controller
@Transactional(isolation = Isolation.REPEATABLE_READ,rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class loginController {
    @Autowired
    private loginImpl loginImpl;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    private static final Logger logger = LoggerFactory.getLogger(loginController.class);
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(path = "/forgetPassword",method = RequestMethod.GET)
    public String forgetPassword(){
        return "/forgetPassword";
    }

    @RequestMapping(path = "/operation",method = RequestMethod.GET)
    public String getOperation(){
        return "/operation-result";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(String userName, String userPassword, String code, Model model, HttpSession session, HttpServletResponse response) throws Exception {
        String kaptcha = (String) session.getAttribute("kaptcha");
        if(!kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码错误");
            return "/login";
        }
        int expiredSeconds = 3600 * 24 * 100;
        Map<String,Object> map =  loginImpl.login(userName,userPassword,expiredSeconds);
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/index";
        }else{
            model.addAttribute("userNameMsg",map.get("userNameMsg"));
            model.addAttribute("userPasswordMsg",map.get("userPasswordMsg"));
            return "/login";
        }
    }

    @RequestMapping(path = "/verifyIdentity",method = RequestMethod.POST)
    @ResponseBody
    public String verifyIdentity(String userName, String userEmail) throws Exception {
        Map<String,Object> map = loginImpl.verifyIdentity(userName,userEmail);
        if(map.containsKey("userName")){
            return CommunityUtil.getJSONString(0,"验证成功",map);
        }else{
            return CommunityUtil.getJSONString(1,"验证失败",map);
        }
    }

    @RequestMapping(path = "/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(String userName,String userPassword, String confirmPassword, Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        userName = session.getAttribute("userName").toString();
        Map<String,Object> map = loginImpl.resetPassword(userName,userPassword,confirmPassword);
        if(map == null || map.isEmpty()){
            return CommunityUtil.getJSONString(0,"重置成功");
        }else{
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("confirmPasswordMsg",map.get("confirmPasswordMsg"));
            return CommunityUtil.getJSONString(1,"重置失败",map);
        }
    }

    @RequestMapping(path = "/storeUserName",method = RequestMethod.POST)
    @ResponseBody
    public String storeUserName(String userName, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        return CommunityUtil.getJSONString(0);
    }
}
