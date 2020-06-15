package cxm.shop.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.portal.pojo.CartItem;

/**
 * 
 * @author	xiaoman
 * @Date 2020年2月23日下午2:19:24
 */
public interface ICartService {
	TaotaoResult addCatItem(long itemId,int num,HttpServletRequest request,HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response);
	TaotaoResult deleteCartItem(long itemId,HttpServletRequest request, HttpServletResponse response);
}
