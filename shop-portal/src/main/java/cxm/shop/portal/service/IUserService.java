package cxm.shop.portal.service;

import cxm.shop.pojo.TbUser;

/**
 * 用户管理service
 * @author	xiaoman
 * @Date 2020年2月23日下午12:14:25
 */
public interface IUserService {
	TbUser getUserByToken(String token);
}
