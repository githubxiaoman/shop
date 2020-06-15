package cxm.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cxm.shop.service.IItemParamItemService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月8日下午8:31:24
 */
@Controller
public class ItemParamItemController {
	@Autowired
	private IItemParamItemService iItemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	public String showItemParamItem(@PathVariable Long itemId,Model model) {
		try {
			String string = iItemParamItemService.getItemParamByItemId(itemId);
			model.addAttribute("itemParam",string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "item";
		
	}
}
