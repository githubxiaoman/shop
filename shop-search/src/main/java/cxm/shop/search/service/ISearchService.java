package cxm.shop.search.service;

import cxm.shop.search.pojo.SearchResult;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月15日下午7:10:53
 */
public interface ISearchService {
	SearchResult search(String queryString,int page,int rows) throws Exception ;
}
