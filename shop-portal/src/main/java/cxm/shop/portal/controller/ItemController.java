package cxm.shop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.pojo.TbItem;
import cxm.shop.portal.pojo.Item;
import cxm.shop.portal.pojo.ItemInfo;
import cxm.shop.portal.service.IItemService;

/**
 * 商品详情页面展示
 * @author	xiaoman
 * @Date 2020年2月17日下午12:00:32
 */
@Controller
public class ItemController {
	@Autowired
	private IItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId,Model model) {
		ItemInfo item=itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	//根据商品id获取商品描述                                                     解决respon乱码
	@RequestMapping(value="/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDescById(@PathVariable Long itemId) {
		String itemdesc=itemService.getItemDescById(itemId);
		return itemdesc;
	}
	//根据商品id获取商品描述                                                     解决respon乱码
	@RequestMapping(value="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String itemdesc=itemService.getItemParam(itemId);
		return itemdesc;
	}
}
