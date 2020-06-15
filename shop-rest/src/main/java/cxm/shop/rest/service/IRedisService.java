package cxm.shop.rest.service;

import cxm.shop.common.utils.TaotaoResult;

/**
 *	redis同步删除 service接口
 * @author	xiaoman
 * @Date 2020年2月13日下午12:29:06
 */
public interface IRedisService {
	public TaotaoResult syncContent(long contentCid) throws Exception;
}
