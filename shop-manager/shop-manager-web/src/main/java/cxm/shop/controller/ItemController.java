/**
 * 
 */
package cxm.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbItem;
import cxm.shop.service.IItemService;

/**
 * 商品的Controller
 * @author	xiaoman
 * @Date 2020年2月3日上午11:32:48
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private IItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody				//获取路径上的参数
	public TbItem findItemById(@PathVariable Long itemId) {
		try {
			return itemService.findItemById(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 显示商品所有数据
	 *@date 2020年2月7日下午3:51:42
	 * @param page 当前页
	 * @param rows 行数
	 * @return     
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUIDataGridResult getItemAll(Integer page,Integer rows) {
		try {
			EUIDataGridResult result = itemService.getItemAll(page, rows);
			System.out.println("result.getRows()"+result.getRows());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}
	//添加商品
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult insertItem(TbItem tbItem,String desc,String itemParams) {
		System.out.println(tbItem.getCid());
		System.out.println(tbItem);
		System.out.println(tbItem.getTitle());
		TaotaoResult result=null;
		try {
			if(tbItem!=null)
			result = itemService.insertItem(tbItem,desc,itemParams);
		} catch (Exception e) {
			System.out.println("出现错误"+e);
		}
		
		return result;
		
	}
	
}
