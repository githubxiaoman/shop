package cxm.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbItemParam;
import cxm.shop.service.IItemParamService;

/**
 * 商品规格参数controller
 * @author	xiaoman
 * @Date 2020年2月8日下午1:34:07
 */
@Controller
@RequestMapping("/item/param" )
public class ItemParamController {
	@Autowired
	private IItemParamService iItemParamService;
	
	//显示所有商品类目参数
	@RequestMapping("/list")
	@ResponseBody
	public EUIDataGridResult getItemParamAll(Integer page,Integer rows) {
		try {
			 System.out.println("sssssssss");
			return iItemParamService.getItemParamAll(page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	///item/param/query/itemcatid/
	//根据商品类型id查询商品规格参宿
	@RequestMapping("/query/itemcatid/{itemcatid}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemcatid) {
		TaotaoResult result=null;
		try {
			result = iItemParamService.getItemParamByCid(itemcatid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	//添加商品规格参数
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData ) {
		TbItemParam itemParam=new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result=null;
		try {
			result = iItemParamService.saveItemParam(itemParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
}
