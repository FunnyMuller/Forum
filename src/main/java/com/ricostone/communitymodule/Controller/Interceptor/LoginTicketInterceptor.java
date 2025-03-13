package com.ricostone.communitymodule.Controller.Interceptor;

import com.ricostone.communitymodule.Entity.LoginTicket;
import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.Impl.defineInformationImpl;
import com.ricostone.communitymodule.Service.Impl.loginImpl;
import com.ricostone.communitymodule.Util.CookieUtil;
import com.ricostone.communitymodule.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author RicoStone
 * @date 2023/12/6
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

   @Autowired
   private loginImpl loginImpl;

   @Autowired
   private HostHolder hostHolder;

   @Autowired
   private defineInformationImpl defineInformationImpl;

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	  // 从cookie中获取凭证
	  String ticket = CookieUtil.getValue(request, "ticket");

	  if (ticket != null) {
		 // 查询凭证
		 LoginTicket loginTicket = loginImpl.findLoginTicket(ticket);
		 // 检查凭证是否有效
		 if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
			// 根据凭证查询用户
			User user = loginImpl.findUserById(loginTicket.getUserId());
			// 在本次请求中持有用户
			hostHolder.setUser(user);
		 }
	  }
	  return true;
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	  User user = hostHolder.getUser();
	  if (user != null && modelAndView != null) {
		 modelAndView.addObject("loginUser", user);
	  }
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	  hostHolder.clear();
   }
}

