package cxm.shop.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.JsonUtils;
import cxm.shop.rest.pojo.CatResult;
import cxm.shop.rest.service.IItemCatService;

/**
 * 前台页面:商品分类展示功能:controller
 * @author	xiaoman
 * @Date 2020年2月9日下午8:29:53
 */
@Controller
public class ItemCatController {
	@Autowired
	private IItemCatService itemCatService;
	
	//使用jsonp实现:接收页面传递参数.参数就是方法的名称.返回一个json数据.将json数据包装成一句JS代码
	//URL_Serv: "http://127.0.0.1:8083/rest/itemcat/all?callback=category.getDataService",
	//*****方法1:spring处理json乱码时,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8"
	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		
		try {
			CatResult catResult = itemCatService.getItemCatList();
			//将pojoz转换字符串
			String json = JsonUtils.objectToJson(catResult);
			//拼装返回值
			String result=callback+"("+json+");";
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	////*******spring处理json乱码时,方法2:spring4.1之后
	/*MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
	mappingJacksonValue.setJsonpFunction(callback);*/
	@RequestMapping(value="/itemcat/list2")
	@ResponseBody
	public Object getItemCatList2(String callback) {
		try {
			CatResult catResult = itemCatService.getItemCatList();
			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
}
