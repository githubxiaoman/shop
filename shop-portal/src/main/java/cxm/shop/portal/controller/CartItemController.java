package cxm.shop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.portal.pojo.CartItem;
import cxm.shop.portal.service.ICartService;

/**
 * 购物车controller
 * @author	xiaoman
 * @Date 2020年2月23日下午4:41:07
 */
@Controller
@RequestMapping("/cart")
public class CartItemController {
	@Autowired
	private ICartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId
			,@RequestParam(defaultValue="1")Integer num
			,HttpServletRequest request,HttpServletResponse response) {
		TaotaoResult result = cartService.addCatItem(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,HttpServletResponse response,Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}
	//删除购物车中的商品
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request,HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}
}
