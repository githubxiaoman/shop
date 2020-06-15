package cxm.shop.order.service.Imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbOrderItemMapper;
import cxm.shop.mapper.TbOrderMapper;
import cxm.shop.mapper.TbOrderShippingMapper;
import cxm.shop.order.dao.JedisClient;
import cxm.shop.order.service.IOrderService;
import cxm.shop.pojo.TbOrder;
import cxm.shop.pojo.TbOrderItem;
import cxm.shop.pojo.TbOrderShipping;

/**
 * 订单管理service
 * @author	xiaoman
 * @Date 2020年2月24日下午4:46:09
 */
@Service
public class OrderServiceImp implements IOrderService {
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;//订单号初始化
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;//订单明细key
	
	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		
		//获得订单号,补全pojo的属性
		String string = jedisClient.get(ORDER_GEN_KEY);
		if(StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId=jedisClient.incr(ORDER_GEN_KEY);
		//向订单表中插入记录
		order.setOrderId(orderId+"");
		order.setStatus(1);//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
		Date date=new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setBuyerRate(0);//状态：0:未评价,1:已评价
		orderMapper.insert(order);
		//插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			//补全订单明细
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setOrderId(orderId+"");
			tbOrderItem.setId(orderDetailId+"");
			orderItemMapper.insert(tbOrderItem);
		}
		//插入物流表
		//补全物流表
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}

	
	
	
	
	
}
