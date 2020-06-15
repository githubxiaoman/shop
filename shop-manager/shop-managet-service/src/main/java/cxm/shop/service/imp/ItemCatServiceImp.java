package cxm.shop.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cxm.shop.common.pojo.EUITreeNode;
import cxm.shop.mapper.TbItemCatMapper;
import cxm.shop.pojo.TbItemCat;
import cxm.shop.pojo.TbItemCatExample;
import cxm.shop.pojo.TbItemCatExample.Criteria;
import cxm.shop.service.IItemCatService;

/**
 *	ItemCat实现类
 * @author	xiaoman
 * @Date 2020年2月3日下午3:52:29
 */
@Service
public class ItemCatServiceImp implements IItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUITreeNode> getCatList(long parentId) throws Exception {
		//创建查询条件
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//转换为List<EUITreeNode>列表
		List<EUITreeNode> resultList=new ArrayList<>();
		//将列表封装到EUITreeNode列表中
		for (TbItemCat itemCat : list) {
			EUITreeNode node=new EUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

}
