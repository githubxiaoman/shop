package cxm.shop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.order.pojo.Order;
import cxm.shop.order.service.IOrderService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月24日下午5:44:57
 */
@Controller
public class OrderController {
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order) {
		try {
			TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
}
