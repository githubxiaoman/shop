package cxm.shop.rest.service;

import cxm.shop.rest.pojo.CatResult;

/**
 * 前台页面:商品分类展示功能:service接口
 * @author	xiaoman
 * @Date 2020年2月9日下午8:04:32
 */
public interface IItemCatService {
	public CatResult getItemCatList() throws Exception;
}
