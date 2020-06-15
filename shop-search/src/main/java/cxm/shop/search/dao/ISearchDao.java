package cxm.shop.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cxm.shop.search.pojo.SearchResult;

/**
 * solr查询 数据访问层接口
 * @author	xiaoman
 * @Date 2020年2月14日下午2:59:21
 */
public interface ISearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}
