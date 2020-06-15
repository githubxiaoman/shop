package cxm.shop.rest.service.imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.JsonUtils;
import cxm.shop.mapper.TbContentMapper;
import cxm.shop.pojo.TbContent;
import cxm.shop.pojo.TbContentExample;
import cxm.shop.pojo.TbContentExample.Criteria;
import cxm.shop.rest.dao.JedisClient;
import cxm.shop.rest.service.IContentService;

/**
 *内容管理:service实现类
 * @author	xiaoman
 * @Date 2020年2月10日下午6:58:00
 */
@Service
public class ContentServiceImp implements IContentService{
	@Autowired
	private TbContentMapper contentmapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long contentCid) throws Exception {
		//从缓存中取内容
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			if(!StringUtils.isBlank(result)) {
				//有值将字符串转换成list
				List<TbContent> list = JsonUtils.jsonToList(result, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据内容分类id查询分类列表
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list = contentmapper.selectByExampleWithBLOBs(example);
		
		//向缓存中添加内容
		try {
			//把list转换成字符串
			String cacheString=JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
