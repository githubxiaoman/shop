package cxm.shop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbContent;
import cxm.shop.rest.service.IContentService;

/**
 * 内容管理:controller
 * @author	xiaoman
 * @Date 2020年2月10日下午7:01:43
 */
@RequestMapping("/content")
@Controller
public class ContentController {
	@Autowired
	private IContentService contentService;
	
	// /rest/content/list/{contentCategoryId}
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable long contentCategoryId){
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return TaotaoResult.ok(list);
		
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
}
