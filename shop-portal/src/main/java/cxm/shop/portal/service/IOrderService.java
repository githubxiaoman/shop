package cxm.shop.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cxm.shop.portal.pojo.Order;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月24日下午11:24:21
 */
public interface IOrderService {
	public String createOrder(Order order,HttpServletRequest request,HttpServletResponse response);
}
