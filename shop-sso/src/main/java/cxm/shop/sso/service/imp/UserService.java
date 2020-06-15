package cxm.shop.sso.service.imp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cxm.shop.common.utils.CookieUtils;
import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.JsonUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbUserMapper;
import cxm.shop.pojo.TbUser;
import cxm.shop.pojo.TbUserExample;
import cxm.shop.pojo.TbUserExample.Criteria;
import cxm.shop.sso.dao.JedisClient;
import cxm.shop.sso.service.IUserService;

/**
 * 用户管理service
 * @author	xiaoman
 * @Date 2020年2月20日下午8:53:00
 */
@Service
public class UserService  implements IUserService{
	@Autowired
	private TbUserMapper usermapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private int SSO_SESSION_EXPIRE;
	//数据类型校验
	@Override
	public TaotaoResult checkDate(String content, int type) {
		//创建查询条件
		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();
		//type为类型，可选参数1、2、3分别代表username、phone、email
		//用户名校验
		if(type==1) {
			criteria.andUsernameEqualTo(content);
		//手机校验
		}else if(type==2) {
			criteria.andPhoneEqualTo(content);
		//邮箱校验
		}else if(type==3) {
			criteria.andEmailEqualTo(content);
		}
		//执行查询
		List<TbUser> list = usermapper.selectByExample(example);
		if(list==null || list.size()==0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}
	//注册用户
	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setUpdated(new Date());
		user.setCreated(new Date());
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));;
		
		usermapper.insert(user);
		return TaotaoResult.ok();
	}
	//用户登录
	@Override
	public TaotaoResult userLogin(String username, String password
			,HttpServletRequest request,HttpServletResponse response) {
		
		TbUserExample example=new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = usermapper.selectByExample(example);
		if(list==null ||list.size()==0) {
			return TaotaoResult.build(400,"用户名密码错误1");
		}
		TbUser user=list.get(0);
		//比对密码
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400,"用户名密码错误2");
		}
		
		//生成token
		String token = UUID.randomUUID().toString();
		user.setPassword(null);
		//把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		//返回token
		
		//添加写cookie,有效期,关闭浏览器就失效
		CookieUtils.setCookie(request, response, "SHOP_TOKEN", token);
		
		return TaotaoResult.ok(token);
	}
	//查询redis中的token信息
	@Override
	public TaotaoResult getUserByToken(String token) {
		//根据token在redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		if(StringUtils.isBlank(token)) {
			return TaotaoResult.build(400, "此session已经过期,请重新登录");
		}
		//更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
	//安全退出
	@Override
	public TaotaoResult userLogout(String token
			,HttpServletRequest request,HttpServletResponse response) {
		String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		//判断token是否合法
		if(StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "此session已经过期,无法安全退出");
		}
		//
		/*CookieUtils.deleteCookie(request, response, "SHOP_TOKEN");*/
		jedisClient.del(REDIS_USER_SESSION_KEY+":"+token);
		return TaotaoResult.ok();
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
