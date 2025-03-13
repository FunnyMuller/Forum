package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Service.Impl.defineInformationImpl;
import com.ricostone.communitymodule.Util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class chooseCommunityController {
   @Autowired
   private defineInformationImpl defineInformationImpl;
   @RequestMapping(path = "/", method = RequestMethod.GET)
   public String redirectToChooseCommunity() {
	  return "redirect:/chooseCommunity";
   }

   @RequestMapping(path = "/chooseCommunity", method = RequestMethod.GET)
   public String chooseCommunity() {
	  return "/chooseCommunity";
   }

   // 获取论坛 id
   @RequestMapping(path = "/chooseCommunity", method = RequestMethod.POST)
   @ResponseBody
   public String getCommunity(int communityId, HttpSession session, ModelAndView model) {
	  session.setAttribute("communityId", communityId);
	  return CommunityUtil.getJSONString(0, "success");
   }
}
