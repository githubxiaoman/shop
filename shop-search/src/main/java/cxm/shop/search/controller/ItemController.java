package cxm.shop.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.search.service.IItemService;

/**
 * 索引库维护
 * @author	xiaoman
 * @Date 2020年2月14日下午2:06:01
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	@Autowired
	private IItemService itemService;
	
	//导入商品数据到索引库
	@RequestMapping("/inputAllItems")
	@ResponseBody
	public  TaotaoResult inputAllItems() throws Exception {
		TaotaoResult result = itemService.inputAllItems();
		return result;
		
	}
	
	
	
}
