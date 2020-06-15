package cxm.shop.order.service;

import java.util.List;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbOrder;
import cxm.shop.pojo.TbOrderItem;
import cxm.shop.pojo.TbOrderShipping;

/**
 * order service层接口
 * @author	xiaoman
 * @Date 2020年2月24日下午4:37:11
 */
public interface IOrderService {
	TaotaoResult createOrder(TbOrder order,List<TbOrderItem> itemList,TbOrderShipping orderShipping);
}
