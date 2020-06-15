package cxm.shop.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.search.pojo.SearchResult;
import cxm.shop.search.service.ISearchService;

/**
 * 商品搜索controller
 * @author	xiaoman
 * @Date 2020年2月15日下午7:55:00
 */
@Controller
public class SearchController {
	@Autowired
	private ISearchService searchService;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("q")String queryString,
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="50")Integer rows) {
		//判定查询条件是否为空
		if(StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		SearchResult search=null;
		try {
			//GET乱码解决
			queryString=new String(queryString.getBytes("iso-8859-1"),"utf-8"); 
			search = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(search);
	}
}
