package cxm.shop.search.mapper;

import java.util.List;

import cxm.shop.search.pojo.Item;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月14日下午1:12:08
 */
public interface Itemmapper {
	List<Item> getItemList() throws Exception;
}
