package cxm.shop.portal.service.imp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.CookieUtils;
import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.JsonUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbUser;
import cxm.shop.portal.pojo.Order;
import cxm.shop.portal.service.IOrderService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月24日下午11:25:30
 */
@Service
public class OrderServiceImp implements IOrderService{
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order
			,HttpServletRequest request,HttpServletResponse response) {
		//补全用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		order.setBuyerNick(user.getUsername());
		order.setOrderId(user.getId()+"");
		try {
			String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));
			//把json装换为taotaoresult
			TaotaoResult taotaoresult = TaotaoResult.format(json);
			if(taotaoresult.getStatus()==200) {
				Object orderId= taotaoresult.getData();
				return orderId.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
