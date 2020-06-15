package cxm.shop.rest.service.imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.JsonUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbItemDescMapper;
import cxm.shop.mapper.TbItemMapper;
import cxm.shop.mapper.TbItemParamItemMapper;
import cxm.shop.pojo.TbItem;
import cxm.shop.pojo.TbItemDesc;
import cxm.shop.pojo.TbItemParamItem;
import cxm.shop.pojo.TbItemParamItemExample;
import cxm.shop.pojo.TbItemParamItemExample.Criteria;
import cxm.shop.rest.dao.JedisClient;
import cxm.shop.rest.service.IItemService;

/**
 * 商品信息管理service
 * @author	xiaoman
 * @Date 2020年2月16日下午5:18:24
 */
@Service
public class ItemService implements IItemService {
	@Autowired
	private TbItemMapper itemmapper;
	
	@Autowired
	private TbItemDescMapper itemDescmapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemmapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {
		try {
			//添加缓存逻辑
			//从缓存中取商信息
			String json = jedisClient.get(REDIS_ITEM_KEY+";"+itemId+":base");
			//判断json是否有值
			if(!StringUtils.isBlank(json)) {
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据商品id查询信息
		TbItem item = itemmapper.selectByPrimaryKey(itemId);
		
		try {
			//将商品信息写入缓存
			//并设置过期时间
			jedisClient.set(REDIS_ITEM_KEY+";"+itemId+":base", JsonUtils.objectToJson(item));
			//设置有效期
			jedisClient.expire(REDIS_ITEM_KEY+";"+itemId+":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(item);
	}
	//根据商品id查询商品描述
	@Override
	public TaotaoResult getItemDesc(long itemId) {
		try {
			//添加缓存逻辑
			//从缓存中取商品描述
			String json = jedisClient.get(REDIS_ITEM_KEY+";"+itemId+":desc");
			//判断json是否有值
			if(!StringUtils.isBlank(json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemDesc itemDesc = itemDescmapper.selectByPrimaryKey(itemId);
		
		try {
			//将商品信息写入缓存
			//并设置过期时间
			jedisClient.set(REDIS_ITEM_KEY+";"+itemId+":desc", JsonUtils.objectToJson(itemDesc));
			//设置有效期
			jedisClient.expire(REDIS_ITEM_KEY+";"+itemId+":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return TaotaoResult.ok(itemDesc);
	}
	//根据商品id查询商品规格参数
	@Override
	public TaotaoResult getItemParamItem(long itemId) {
		try {
			//添加缓存逻辑
			//从缓存中取商品描述
			String json = jedisClient.get(REDIS_ITEM_KEY+";"+itemId+":param");
			//判断json是否有值
			if(!StringUtils.isBlank(json)) {
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemParamItemExample example=new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询条件
		List<TbItemParamItem> list = itemParamItemmapper.selectByExampleWithBLOBs(example);
		if(list!=null && list.size()>0) {
			TbItemParamItem itemParamItem=list.get(0);
			try {
				//将商品信息写入缓存
				//并设置过期时间
				jedisClient.set(REDIS_ITEM_KEY+";"+itemId+":param", JsonUtils.objectToJson(itemParamItem));
				//设置有效期
				jedisClient.expire(REDIS_ITEM_KEY+";"+itemId+":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaotaoResult.ok(itemParamItem);
		}
		return TaotaoResult.build(400,"无此商品的规格参数");
	}
	
}
