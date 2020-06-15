package cxm.shop.portal.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.portal.pojo.SearchResult;
import cxm.shop.portal.service.ISearchService;

/**
 * 商品搜索service
 * @author	xiaoman
 * @Date 2020年2月16日下午3:46:08
 */
@Service
public class SearchService implements ISearchService{
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    
	@Override
	public SearchResult search(String queryString, int page) {
		//调用search服务
		//查询参数
		Map<String, String> param=new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		try {
			//执行调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			//将json装换成java对象
			TaotaoResult taoTaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			if(taoTaoResult.getStatus()==200) {
				SearchResult result = (SearchResult) taoTaoResult.getData();
				return result;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
