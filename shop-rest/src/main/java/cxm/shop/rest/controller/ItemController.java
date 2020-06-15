package cxm.shop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.rest.service.IItemService;

/**
 * 商品信息controller
 * @author	xiaoman
 * @Date 2020年2月16日下午5:21:24
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private IItemService itemService;
	//根据商品id查询商品基础信息
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	//根据商品id查询商品描述
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}
	//根据商品id查询商品描述
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamItem(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParamItem(itemId);
		return result;
	}
	
}
