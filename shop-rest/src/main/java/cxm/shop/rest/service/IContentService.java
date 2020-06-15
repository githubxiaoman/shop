package cxm.shop.rest.service;

import java.util.List;

import cxm.shop.pojo.TbContent;

/**
 * 内容管理:service接口
 * @author	xiaoman
 * @Date 2020年2月10日下午6:56:48
 */
public interface IContentService {
	public List<TbContent> getContentList(long contentCid) throws Exception;
}
