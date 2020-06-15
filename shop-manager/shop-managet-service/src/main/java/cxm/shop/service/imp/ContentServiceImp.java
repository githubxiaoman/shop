package cxm.shop.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cxm.shop.common.pojo.EUIDataGridResult;
import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.mapper.TbContentMapper;
import cxm.shop.pojo.TbContent;
import cxm.shop.pojo.TbContentExample;
import cxm.shop.pojo.TbContentExample.Criteria;
import cxm.shop.service.IContentService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月10日下午4:00:08
 */
@Service
public class ContentServiceImp  implements IContentService{
	@Autowired
	private TbContentMapper contentmapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	//根据内容分类id查询内容
	@Override
	public EUIDataGridResult getContentAll(Long categoryId, int page, int rows) throws Exception {
		TbContentExample example=new TbContentExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentmapper.selectByExample(example);
		EUIDataGridResult result=new EUIDataGridResult();
		PageInfo<TbContent> info=new PageInfo<>(list);
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}
	//添加内容
	@Override
	public TaotaoResult insertContent(TbContent content) throws Exception {
		//补全pojo
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentmapper.insert(content);
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+content.getCategoryId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}
	
	
}
