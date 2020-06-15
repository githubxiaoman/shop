package cxm.shop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbUser;
import cxm.shop.sso.service.IUserService;

/**
 * 用户管理controller
 * @author	xiaoman
 * @Date 2020年2月20日下午9:02:26
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	//校验controller
	//http://sso.taotao.com/user/check/{param}/{type}
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkDate(@PathVariable String param,@PathVariable Integer type,String callback) {
		TaotaoResult result =null;
		if(type!=1 && type !=2 && type!=3) {
			 result = TaotaoResult.build(401, "校验类型错误");
		}
		//校验出错
		if(null !=result) {
			if(null!=callback) {
				MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return callback;
			}
			return result;
		}
		//没有校验问题
		try {
			result = userService.checkDate(param, type);
		} catch (Exception e) {
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//验证是否需要支持jsonp
		if(null !=result) {
			if(null!=callback) {
				MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return callback;
			}
		}
		return result;
	}
	//创建用户
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createUser(TbUser user) {
		TaotaoResult result = null;
		try {
			result = userService.createUser(user);
		}catch(Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
	
	//用户登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username,String password
			,HttpServletRequest request,HttpServletResponse response) {
		try {
			TaotaoResult result = userService.userLogin(username, password,request,response);
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
		}
	}
	//通过token查询用户信息
	@RequestMapping(value="/token/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback) {
		TaotaoResult result =null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//判断是否是jsonp调用
		if(StringUtils.isBlank(callback)) {
			return result;
		}else {
			MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		}
	}
	//安全退出
	@RequestMapping(value="/logout/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object userLogout(@PathVariable String token,String callback) {
		TaotaoResult result =null;
		try {
			result = userService.userLogout(token, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//判断是否需jsonp调用
		if(StringUtils.isBlank(callback)) {
			return result;
		}else {
			MappingJacksonValue jacksonValue=new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		}
	}
	
}
