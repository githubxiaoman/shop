package cxm.shop.service;

import java.util.List;

import cxm.shop.common.pojo.EUITreeNode;
import cxm.shop.common.utils.TaotaoResult;

/**
 * 内容分类管理service接口
 * @author	xiaoman
 * @Date 2020年2月10日下午1:38:35
 */
public interface IContentCategoryService {
	public List<EUITreeNode> getCategoryList(Long parentId) throws Exception;
	public TaotaoResult createCategoryNode(Long parentId,String name) throws Exception;
	public TaotaoResult updateCategoryByName(long id,String name) throws Exception;
	public TaotaoResult deleteCategoryNode(long parentId,long id) throws Exception;
}
