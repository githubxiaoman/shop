package cxm.shop.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.IDUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbItemDescMapper;
import cxm.shop.mapper.TbItemMapper;
import cxm.shop.mapper.TbItemParamItemMapper;
import cxm.shop.pojo.TbItem;
import cxm.shop.pojo.TbItemDesc;
import cxm.shop.pojo.TbItemExample;
import cxm.shop.pojo.TbItemExample.Criteria;
import cxm.shop.pojo.TbItemParamItem;
import cxm.shop.service.IItemService;

/**
 * 商品业务实现类
 * @author	xiaoman
 * @Date 2020年2月3日上午11:24:16
 */
@Service
public class ItemServiceImp implements IItemService{
	//创建mapper代理对象
	@Autowired
	private TbItemMapper itemmapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemmapper;
	
	@Autowired
	private TbItemDescMapper itemDescmapper;
	
	@Override
	public TbItem findItemById(long itemId) throws Exception{
		/*TbItem item = itemmapper.selectByPrimaryKey(itemId);*/
		//通过添加查询条件查询
		TbItemExample example=new TbItemExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemmapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 查询所有商品,使用PageHelper分页插件,实现分页功能
	 */
	@Override
	public EUIDataGridResult getItemAll(int page,int rows) throws Exception {
		//查询商品列表
		TbItemExample example=new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemmapper.selectByExample(example);
		//创建一个返回值对象
		EUIDataGridResult result=new EUIDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	/**
	 * 商品添加
	 */
	@Override
	public TaotaoResult insertItem(TbItem tbItem,String desc,String itemParam) throws Exception {
		//补全商品数据
		//添加商品ID
		Long itemId=IDUtils.getItemId();
		tbItem.setId(itemId);
		//商品状态:1.正常 2.下架,3-删除
		tbItem.setStatus((byte)1);
		//创建,修改时间
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		itemmapper.insert(tbItem);
		//添加商品描述
		TaotaoResult result = insertItemDesc(itemId, desc);
		//添加商品规格参数
		TaotaoResult paramResult = insertItemParamItem(itemId,itemParam);
		if(result.getStatus()!=200 && paramResult.getStatus()!=200) {
			throw new Exception();
		}
		
		return TaotaoResult.ok();
	}
	//添加商品描述
	private TaotaoResult  insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc=new TbItemDesc();
		//补全TbItemDesc
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescmapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertItemParamItem(Long itemId,String itemParam) {
		TbItemParamItem itemParamItem=new TbItemParamItem();
		//补全itemParamItem
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemmapper.insert(itemParamItem);
		return TaotaoResult.ok();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
