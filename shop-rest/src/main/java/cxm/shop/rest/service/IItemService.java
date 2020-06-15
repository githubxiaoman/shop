package cxm.shop.rest.service;

import cxm.shop.common.utils.TaotaoResult;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月16日下午5:17:03
 */
public interface IItemService {
	TaotaoResult getItemBaseInfo(long itemId);
	TaotaoResult getItemDesc(long itemId);
	TaotaoResult getItemParamItem(long itemId);
}
