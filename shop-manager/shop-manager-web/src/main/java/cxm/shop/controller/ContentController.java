package cxm.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbContent;
import cxm.shop.service.IContentService;

/**
 * 内容管理controller
 * @author	xiaoman
 * @Date 2020年2月10日下午3:57:44
 */
@RequestMapping("/content")
@Controller
public class ContentController {
	@Autowired
	private IContentService contentService;
	
	//http://localhost:8082/content/query/list?categoryId=0&page=1&rows=20
	@RequestMapping("/query/list")
	@ResponseBody
	public EUIDataGridResult getContentAll(Long categoryId,int page,int rows) {
		try {
			EUIDataGridResult result = contentService.getContentAll(categoryId, page, rows);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//http://localhost:8082/content/save
	//添加内容
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		try {
			TaotaoResult result = contentService.insertContent(content);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
