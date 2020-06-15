package cxm.shop.portal.service.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbUser;
import cxm.shop.portal.service.IUserService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月23日下午12:15:34
 */
@Service
public class UserServiceImp implements IUserService{
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_TOKEN_URL}")
	private String SSO_TOKEN_URL;
	@Value("${SSO_LOGIN_URL}")
	public String SSO_LOGIN_URL;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//使用httpclient访问sso服务
			String json = HttpClientUtil.doGet(SSO_BASE_URL+SSO_TOKEN_URL+ token);
			TaotaoResult taotaoresult = TaotaoResult.formatToPojo(json, TbUser.class);
			if(taotaoresult.getStatus()==200) {
				TbUser user=(TbUser) taotaoresult.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
