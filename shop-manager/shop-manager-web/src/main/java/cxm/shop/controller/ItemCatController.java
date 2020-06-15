package cxm.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.pojo.EUITreeNode;
import cxm.shop.service.IItemCatService;

/**
 *	itemCat的controller
 * @author	xiaoman
 * @Date 2020年2月3日下午4:25:12
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private IItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUITreeNode> getCatList(@RequestParam(value="id",defaultValue="0") Long parentId) throws Exception{
		List<EUITreeNode> list= itemCatService.getCatList(parentId);
		if(list!=null && list.size()>0) {
			return list;
		}else {
			System.out.println("list为空");
			return null;
		}
		
	}
}
