package cxm.shop.service;


import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbItem;

/**
 * 商品业务实现接口
 * @author	xiaoman
 * @Date 2020年2月3日上午11:24:46
 */
public interface IItemService {
	public TbItem findItemById(long itemId) throws Exception;
	public EUIDataGridResult getItemAll(int page,int rows) throws Exception;
	public TaotaoResult insertItem(TbItem tbItem,String desc,String itemParam) throws Exception;
}
