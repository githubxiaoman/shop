package cxm.shop.service;

import java.util.List;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbItemParam;

/**
 * 商品规格参数service接口
 * @author	xiaoman
 * @Date 2020年2月8日下午1:24:30
 */
public interface IItemParamService {
	public TaotaoResult getItemParamByCid(Long cid) throws Exception;
	public TaotaoResult saveItemParam(TbItemParam itemParam)throws Exception;
	public EUIDataGridResult getItemParamAll(Integer page,Integer rows) throws Exception;
}
