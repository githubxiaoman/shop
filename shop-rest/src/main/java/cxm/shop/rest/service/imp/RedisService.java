package cxm.shop.rest.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.rest.dao.JedisClient;
import cxm.shop.rest.service.IRedisService;

/**
 *	redis同步删除 service实现类
 * @author	xiaoman
 * @Date 2020年2月13日下午12:30:26
 */
@Service
public class RedisService implements IRedisService{
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public TaotaoResult syncContent(long contentCid) throws Exception {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TaotaoResult.ok();
	}

	
}
