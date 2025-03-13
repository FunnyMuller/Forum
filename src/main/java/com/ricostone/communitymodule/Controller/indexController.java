package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Entity.Post;
import com.ricostone.communitymodule.Entity.defineInformation;
import com.ricostone.communitymodule.Service.Impl.defineInformationImpl;
import com.ricostone.communitymodule.Service.Impl.loginImpl;
import com.ricostone.communitymodule.Service.Impl.postImpl;
import com.ricostone.communitymodule.Util.CommunityNormalValue;
import com.ricostone.communitymodule.Util.CommunityUtil;
import com.ricostone.communitymodule.Util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class indexController {
   private static final Logger logger = LoggerFactory.getLogger(indexController.class);
   @Autowired
   private postImpl postImpl;
   @Autowired
   private loginImpl loginImpl;
   @Autowired
   private defineInformationImpl defineInformationImpl;

   /**
	* 获取 index 页面
	*
	* @return index 页面
	*/
   @RequestMapping(path = "/index", method = RequestMethod.GET)
   public String getIndex(Model model, HttpSession session) {
	  int communityId = (int)session.getAttribute("communityId");
	  /*
	   * 获取网页帖子数据
	   */
	  List<Post> postList = postImpl.getPost(0, CommunityNormalValue.LIMIT, communityId);
	  List<Map<String, Object>> mapList = new ArrayList<>();
	  for (Post post : postList) {
		 Map<String, Object> map = new HashMap<>();
		 map.put("postTitle", post.getPostTitle());
		 map.put("postId", post.getPostId());
		 String content = post.getPostContent();
		 if (content.length() > 100) {
			content = content.substring(0, 100) + "...";
		 }
		 map.put("postContent", content);
		 map.put("postUserId", post.getPostUserId());
		 map.put("postEntityType", post.getPostEntityType());
		 map.put("postEntityStatus", post.getPostEntityStatus());
		 map.put("postCreateTime", post.getPostCreateTime());
		 map.put("postImageUrl", post.getPostImageUrl());
		 map.put("postUserName", post.getPostUserName());
		 mapList.add(map);
	  }
	  model.addAttribute("mapList", mapList);
	  return "/index";
   }

   /*
   退出登录
	*/
   @RequestMapping(path = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public String logout(HttpServletRequest request) {
	  String ticket = CookieUtil.getValue(request, "ticket");
	  int res = loginImpl.logout(ticket);
	  if (res == 0) return CommunityUtil.getJSONString(1, "退出失败");
	  else return CommunityUtil.getJSONString(0, "退出成功");
   }

   @RequestMapping(path = "/getCommunity", method = RequestMethod.GET)
   public ModelAndView getCommunity(HttpSession session) {
	  int communityId = (int)session.getAttribute("communityId");
	  ModelAndView modelAndView = new ModelAndView("index");
	  defineInformation information = defineInformationImpl.selectDefineInformation(communityId);
	  modelAndView.addObject("information", information);
	  List<Post> postList = postImpl.getPost(0, CommunityNormalValue.LIMIT, communityId);
	  List<Map<String, Object>> mapList = new ArrayList<>();
	  for (Post post : postList) {
		 Map<String, Object> map = new HashMap<>();
		 map.put("postTitle", post.getPostTitle());
		 map.put("postId", post.getPostId());
		 String content = post.getPostContent();
		 if (content.length() > 100) {
			content = content.substring(0, 100) + "...";
		 }
		 map.put("postContent", content);
		 map.put("postUserId", post.getPostUserId());
		 map.put("postEntityType", post.getPostEntityType());
		 map.put("postEntityStatus", post.getPostEntityStatus());
		 map.put("postCreateTime", post.getPostCreateTime());
		 map.put("postImageUrl", post.getPostImageUrl());
		 map.put("postUserName", post.getPostUserName());
		 mapList.add(map);
	  }
	  modelAndView.addObject("mapList", mapList);
	  return modelAndView;
   }
}
