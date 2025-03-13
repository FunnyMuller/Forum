package com.ricostone.communitymodule.Controller.Interceptor;

import com.ricostone.communitymodule.Annotation.LoginRequired;
import com.ricostone.communitymodule.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author RicoStone
 * @date 2023/12/6
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
   @Autowired
   private HostHolder hostHolder;

   /**
	* 拦截器，如果用户没有登录，就跳转到 login 页面
	*
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	*/
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	  if (handler instanceof HandlerMethod) {
		 HandlerMethod handlerMethod = (HandlerMethod) handler;
		 Method method = handlerMethod.getMethod();
		 LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
		 if (loginRequired != null && hostHolder.getUser() == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		 }
	  }
	  return true;
   }
}
