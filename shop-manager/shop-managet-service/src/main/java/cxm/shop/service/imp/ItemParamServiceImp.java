package cxm.shop.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbItemParamMapper;
import cxm.shop.pojo.TbItem;
import cxm.shop.pojo.TbItemParam;
import cxm.shop.pojo.TbItemParamExample;
import cxm.shop.pojo.TbItemParamExample.Criteria;
import cxm.shop.service.IItemParamService;

/**
 * 商品规格参数service实现类
 * //当有使用长文本类型的时查询,使用selectByExampleWithBLOBs
 * @author	xiaoman
 * @Date 2020年2月8日下午1:26:02
 */
@Service
public class ItemParamServiceImp  implements IItemParamService{
	@Autowired
	private TbItemParamMapper  itemParammmapper;
	
	//根据商品规格Id查询商品
	@Override
	public TaotaoResult getItemParamByCid(Long cid) throws Exception {
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//提取paramData,使用selectByExampleWithBLOBs
		List<TbItemParam> list = itemParammmapper.selectByExampleWithBLOBs(example);
		if(list!=null && list.size()>0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	//保存商品规格
	@Override
	public TaotaoResult saveItemParam(TbItemParam itemParam) throws Exception {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParammmapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	@Override
	public EUIDataGridResult getItemParamAll(Integer page,Integer rows) throws Exception {
		TbItemParamExample example=new TbItemParamExample();
		//使用分页插件
		PageHelper.startPage(page, rows);
		//当使用长文本类型的时候,提取paramData,使用selectByExampleWithBLOBs
		List<TbItemParam> list = itemParammmapper.selectByExampleWithBLOBs(example);
		EUIDataGridResult result=new EUIDataGridResult();
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	
}
