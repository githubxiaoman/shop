package cxm.shop.portal.service;

import cxm.shop.pojo.TbItem;
import cxm.shop.portal.pojo.Item;
import cxm.shop.portal.pojo.ItemInfo;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月17日上午11:51:12
 */
public interface IItemService {
	ItemInfo getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
}
