package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Service.Impl.publishImpl;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author RicoStone
 * @date 2023/12/7
 */
@Controller
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class discussPostController {
    private static final Logger logger =  LoggerFactory.getLogger(discussPostController.class);
    @Autowired
    private publishImpl publishImpl;
    @RequestMapping(path = "/publish", method = RequestMethod.GET)
    public String getPublishPage(){
        return "/publish";
    }
    @RequestMapping(path = "/publish", method = RequestMethod.POST)
    public String publish(String postTitle, String postContent,String postImageUrl, Model model){
        Map<String,Object> map =  publishImpl.publishPost(postTitle,postContent,postImageUrl);
        if(map == null || map.isEmpty()){
            return "redirect:/index";
        }else{
            model.addAttribute("titleMsg",map.get("titleMsg"));
            model.addAttribute("contentMsg",map.get("contentMsg"));
            return "/publish";
        }
    }
}
