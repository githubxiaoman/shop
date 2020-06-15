package cxm.shop.portal.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cxm.shop.common.utils.CookieUtils;
import cxm.shop.pojo.TbUser;
import cxm.shop.portal.service.imp.UserServiceImp;

/**
 * 登录拦截器
 * @author	xiaoman
 * @Date 2020年2月23日上午11:26:37
 */
public class LoginIntercepter implements HandlerInterceptor {
	@Autowired
	private UserServiceImp userService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// ModelAndView执行之后执行
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// handler执行之后,返回ModelAndView之前
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// handler执行之前执行
		//获取浏览器上的cookie,为SHOP_TOKEN
		String token = CookieUtils.getCookieValue(arg0, "SHOP_TOKEN");
		//根据token提取用户信息
		TbUser user = userService.getUserByToken(token);
		//判断token是否取到user信息
		if(null==user) {
			//没有取到,跳转到sso的登录页面
			arg1.sendRedirect(userService.SSO_BASE_URL+userService.SSO_LOGIN_URL+"?redirect="+arg0.getRequestURL());
			return false;
		}
		arg0.setAttribute("user", user);
		return true;
	}

}
