package cxm.shop.portal.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.JsonUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbContent;
import cxm.shop.portal.service.IContentService;

/**
 *	调用服务层服务,查询内容列表
 * @author	xiaoman
 * @Date 2020年2月11日上午4:10:54
 */
@Service
public class ContentServiceImp implements IContentService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;//基础url
		
	@Value("${REST_INDEX_URL}")
	private String REST_INDEX_URL;//首页广告url
	
	
	@Override
	public String getContentList() throws Exception {
		//使用httpClien调用服务
		String url=REST_BASE_URL+REST_INDEX_URL;
		String result = HttpClientUtil.doGet(url);
		if(!StringUtils.isBlank(result)) {
			//把字符串转化成taotaoResult
			try {
				TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
				//取内容列表
				List<TbContent> list= (List<TbContent>) taotaoResult.getData();
				//创建页面需求的List
				List<Map> resultList=new ArrayList<>();
				for (TbContent content : list) {
					Map map=new HashMap<>();
					map.put("src", content.getPic());
					map.put("height", 240);
					map.put("width", 670);
					map.put("alt", content.getSubTitle());
					map.put("srcB", content.getPic2());
					map.put("widthB", 550);
					map.put("heightB", 240);
					map.put("href", content.getUrl());
					resultList.add(map);
				}
				return JsonUtils.objectToJson(resultList);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
