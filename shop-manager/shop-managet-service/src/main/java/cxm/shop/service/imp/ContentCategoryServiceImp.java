package cxm.shop.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cxm.shop.common.pojo.EUITreeNode;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbContentCategoryMapper;
import cxm.shop.pojo.TbContentCategory;
import cxm.shop.pojo.TbContentCategoryExample;
import cxm.shop.pojo.TbContentCategoryExample.Criteria;
import cxm.shop.service.IContentCategoryService;

/**
 *	内容分类管理service实现类
 * @author	xiaoman
 * @Date 2020年2月10日下午1:38:35
 */
@Service
public class ContentCategoryServiceImp implements IContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	/**
	 * 根据parenId查询节点列表	
	 */
	@Override
	public List<EUITreeNode> getCategoryList(Long parentId) throws Exception {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询结果
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//返回结果
		List<EUITreeNode> resultList=new ArrayList<EUITreeNode>();
		for (TbContentCategory category : list) {
			EUITreeNode node=new EUITreeNode();
			node.setId(category.getId());	
			node.setText(category.getName());
			node.setState(category.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	//内容分类节点添加
	@Override
	public TaotaoResult createCategoryNode(Long parentId, String name) throws Exception {
		//创建一个pojo
		TbContentCategory category=new TbContentCategory();
		category.setName(name);
		category.setParentId(parentId);
		category.setIsParent(false);
		category.setStatus(1);
		category.setSortOrder(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		//添加记录,需要返回主键信息
		contentCategoryMapper.insert(category);
		//查看父节点的Isparent是否为true,如果不是true改为true
		TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		return TaotaoResult.ok(category);
	}
	
	//根据节点id修改节点名称
	@Override
	public TaotaoResult updateCategoryByName(long id, String name) throws Exception {
		TbContentCategory category=new TbContentCategory();
		category.setId(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(category);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteCategoryNode(long parentId, long id) throws Exception {
		//删除节点id为Id的值
		contentCategoryMapper.deleteByPrimaryKey(id);
		//获取parentId符合值得category
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		 criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//判断数据库中是否有parentId为parentId的category
		if(list==null && list.size()==0) {
			TbContentCategory parent=new TbContentCategory();
			parent.setId(parentId);
			parent.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKeySelective(parent);
		}
		return TaotaoResult.ok();
	}
	
	
	
	
	
	
	

}
