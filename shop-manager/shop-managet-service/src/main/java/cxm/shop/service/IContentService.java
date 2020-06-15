package cxm.shop.service;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbContent;

/**
 *	内容管理service 接口
 * @author	xiaoman
 * @Date 2020年2月10日下午3:56:59
 */
public interface IContentService {
	public EUIDataGridResult getContentAll(Long categoryId,int page,int rows) throws Exception;
	public TaotaoResult insertContent(TbContent content) throws Exception;



}
