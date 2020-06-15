package cxm.shop.portal.service.imp;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.CookieUtils;
import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.JsonUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbItem;
import cxm.shop.portal.pojo.CartItem;
import cxm.shop.portal.service.ICartService;

/**
 * 购物车Service
 * @author	xiaoman
 * @Date 2020年2月23日下午2:20:32
 */
@Service
public class CartServiceImp implements ICartService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	//添加商品购物车
	@Override
	public TaotaoResult addCatItem(long itemId, int num
			,HttpServletRequest request,HttpServletResponse response) {
		//取商品信息
		CartItem cartItem=null;
		//取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		if(null==itemList ) {
			cartItem=new CartItem();
			//根据商品id查询商品详细信息
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			//把json装换为java对象
			TaotaoResult taotaoresult = TaotaoResult.formatToPojo(json, TbItem.class);
			if(taotaoresult.getStatus()==200) {
				TbItem item=(TbItem) taotaoresult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setPrice(item.getPrice());
				cartItem.setImage(item.getImage()==null?"":item.getImage().split(",")[0]);
				cartItem.setNum(num);
			}
			itemList=new ArrayList<>();
			itemList.add(cartItem);
		}else {
			for (CartItem citem : itemList) {
				//如果存在此商品
				if(citem.getId()==itemId) {
					//增加商品数量
					citem.setNum(citem.getNum()+num);
					cartItem=citem;
					break;
				}
			}
		}
		//购物车列表写入cookie
		CookieUtils.setCookie(request, response, "SHOP_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}
	//从cookie中取商品列表
	private List<CartItem> getCartItemList(HttpServletRequest request){
		String cartJson = CookieUtils.getCookieValue(request, "SHOP_CART",true);
		//List<CartItem> list = null;
		/*if(cartJson==null) {
			list= new ArrayList<>();
		}else{
			list = JsonUtils.jsonToList(cartJson, CartItem.class);
		}*/
		return cartJson==null?null:JsonUtils.jsonToList(cartJson, CartItem.class);
	}
	//从cookie中取商品列表
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}
	//根据商品id删除购物车商品
	@Override
	public TaotaoResult deleteCartItem(long itemId,HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车商品列表
		List<CartItem> cartItemList = getCartItemList(request);
		//从列表中找到商品
		for (CartItem cartItem : cartItemList) {
			if(cartItem.getId()==itemId) {
				cartItemList.remove(cartItem);
				break;
			}
		}
		//将购物车重新写入cookie
		CookieUtils.setCookie(request, response, "SHOP_CART", JsonUtils.objectToJson(cartItemList), true);
		return TaotaoResult.ok();
	}
}
