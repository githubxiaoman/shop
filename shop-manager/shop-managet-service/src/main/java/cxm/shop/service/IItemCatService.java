package cxm.shop.service;

import java.util.List;

import cxm.shop.common.pojo.EUITreeNode;

/**
 *	ItemCat接口
 * @author	xiaoman
 * @Date 2020年2月3日下午3:51:29
 */
public interface IItemCatService {
	List<EUITreeNode> getCatList(long parentId) throws Exception;
}
