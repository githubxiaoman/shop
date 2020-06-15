package cxm.shop.search.service.imp;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cxm.shop.search.dao.ISearchDao;
import cxm.shop.search.pojo.SearchResult;
import cxm.shop.search.service.ISearchService;

/**
 * 搜索service
 * @author	xiaoman
 * @Date 2020年2月15日下午7:35:04
 */
@Service
public class SearchServiceImp  implements ISearchService{
	@Autowired
	private ISearchDao searchdao;

	@Override
	public SearchResult search(String queryString, int page, int rows)throws Exception {
		//创建查询条件
		SolrQuery query=new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置分页
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置默认搜索域
		query.set("df", "item_keywords");
		//设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em");
		//执行查询
		SearchResult result = searchdao.search(query);
		//计算查询结果总页数
		long recordCount = result.getRecordCount();
		long pageCount=recordCount/rows;
		if(recordCount%rows>0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurpage(page);
		return result;
	}

}
