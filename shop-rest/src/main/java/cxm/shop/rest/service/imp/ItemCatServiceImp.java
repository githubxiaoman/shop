package cxm.shop.rest.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cxm.shop.mapper.TbItemCatMapper;
import cxm.shop.pojo.TbItemCat;
import cxm.shop.pojo.TbItemCatExample;
import cxm.shop.pojo.TbItemCatExample.Criteria;
import cxm.shop.rest.pojo.CatNode;
import cxm.shop.rest.pojo.CatResult;
import cxm.shop.rest.service.IItemCatService;

/**
 * 前台页面:商品分类展示功能:service实现类
 * @author	xiaoman
 * @Date 2020年2月9日下午8:05:25
 */
@Service
public class ItemCatServiceImp implements IItemCatService{
	@Autowired
	private TbItemCatMapper itemCatmapper;
	
	@Override
	public CatResult getItemCatList() throws Exception {
		CatResult catResult=new CatResult();
		//返回分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}
	//返回分类列表方法
	private List<?> getCatList(long parentId){
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询的结果集List
		List<TbItemCat> list = itemCatmapper.selectByExample(example);
		//返回值List
		List resultList=new ArrayList<CatNode>();
		//向list中添加节点
		int count=0;
		for (TbItemCat itemCat : list) {
			//判断是否为父节点
			if(itemCat.getIsParent()) {
				CatNode node=new CatNode();
				//*第一层:判断parentId==0
				if(parentId==0) {
					node.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
				}else {
					//**第二层:
					node.setName(itemCat.getName());
				}
				
				node.setUrl("/products/"+itemCat.getId()+".html");
				//通过递归方法获取
				node.setItem(getCatList(itemCat.getId()));
				resultList.add(node);
				count++;
				//第一层只取14条记录
				if(parentId==0 &&count>=14) {
					break;
				}
				//***如果是叶子节点时.处理
			}else {
				resultList.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
			}
		
			
		}
		return resultList;
		
	}

}
