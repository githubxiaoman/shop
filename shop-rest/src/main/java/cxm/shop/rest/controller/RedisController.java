package cxm.shop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.rest.service.IRedisService;

/**
 * redis同步Controller
 * @author	xiaoman
 * @Date 2020年2月13日下午12:40:22
 */
@Controller
@RequestMapping("/cache/snyc")
public class RedisController {
	@Autowired
	private IRedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public TaotaoResult contentCacheSync(@PathVariable long contentCid) {
		TaotaoResult result=null;
		try {
			result = redisService.syncContent(contentCid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
