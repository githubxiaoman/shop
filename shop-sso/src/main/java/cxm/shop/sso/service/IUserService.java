package cxm.shop.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbUser;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月20日下午8:52:19
 */
public interface IUserService {
	TaotaoResult checkDate(String content,int type);
	TaotaoResult createUser(TbUser user);
	TaotaoResult userLogin(String username,String password,HttpServletRequest request,HttpServletResponse response);
	TaotaoResult getUserByToken(String token);
	TaotaoResult userLogout(String token,HttpServletRequest request,HttpServletResponse response);
}
