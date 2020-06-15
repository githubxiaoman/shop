package cxm.shop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.portal.pojo.CartItem;
import cxm.shop.portal.pojo.Order;
import cxm.shop.portal.service.ICartService;
import cxm.shop.portal.service.IOrderService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月24日下午7:29:25
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IOrderService orderService;
	
	//order/order-cart.html
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,HttpServletResponse response,Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	//创建订单
	@RequestMapping("/create")
	public String createOrder(Order order,Model model,HttpServletRequest request,HttpServletResponse response) {
		try {
			String orderId = orderService.createOrder(order,request,response);
			model.addAttribute("",orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-mm-dd"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", ExceptionUtil.getStackTrace(e));
			return "error/exception";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
