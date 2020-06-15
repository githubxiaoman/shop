package cxm.shop.search.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cxm.shop.search.dao.ISearchDao;
import cxm.shop.search.pojo.Item;
import cxm.shop.search.pojo.SearchResult;

/**
 *solr查询 dao实现类
 * @author	xiaoman
 * @Date 2020年2月14日下午3:00:47
 */
@Repository
public class SearchDaoImp implements ISearchDao{
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		//返回值对象
		SearchResult result=new SearchResult();
		//根据查询条件查询索引库
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		//取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		
		//取结果列表
		List<Item> list=new ArrayList<>();
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument document : solrDocumentList) {
			//创建商品对象
			Item item=new Item();
			item.setId((String) document.get("id"));
			//取高亮显示的结果
			List<String> list2 = highlighting.get(document.get("id")).get("item_title");
			String title ="";
			if(list2!=null && list2.size()>0) {
				title = list2.get(0);
			}else {
				title=(String) document.get("item_title");
			}
			item.setTitle(title);
			item.setSell_point((String) document.get("item_sell_point"));
			item.setPrice((long) document.get("item_price"));
			item.setImage((String) document.get("item_image"));
			item.setCategory_name((String) document.get("item_category_name"));
			list.add(item);
		}
		result.setItemList(list);
		return result;
	}

}
