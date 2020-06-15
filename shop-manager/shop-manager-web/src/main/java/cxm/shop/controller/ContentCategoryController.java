package cxm.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.pojo.EUITreeNode;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.service.IContentCategoryService;

/**
 * 前台页面:商品分类展示功能:controller
 * @author	xiaoman
 * @Date 2020年2月10日下午1:52:28
 */

@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {
	@Autowired
	private IContentCategoryService contentCategoryService;
	//内容分类展示
	@RequestMapping("/list")
	@ResponseBody
	public List<EUITreeNode> getCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		try {
			List<EUITreeNode> result = contentCategoryService.getCategoryList(parentId);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	///content/category/create
	//内容分类节点添加
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createCategoryNode(Long parentId,String name) {
		try {
			TaotaoResult result = contentCategoryService.createCategoryNode(parentId, name);
			return  result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
	///content/category/update",{id:node.id,name:node.text}
	//内容分类节点重命名
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateCategoryByName(long id,String name) {
		try {
			TaotaoResult result = contentCategoryService.updateCategoryByName(id, name);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	///content/category/delete/",{parentId:node.parentId,id:node.id}
	//内容分类节点删除
	public TaotaoResult deleteCategoryNode(long parentId,long id) {
		try {
			return contentCategoryService.deleteCategoryNode(parentId, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
}
