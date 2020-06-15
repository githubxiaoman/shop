package cxm.shop.rest.pojo;

import java.util.List;

import cxm.shop.pojo.TbOrder;
import cxm.shop.pojo.TbOrderItem;
import cxm.shop.pojo.TbOrderShipping;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月24日下午5:24:07
 */
public class Order extends TbOrder {
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
