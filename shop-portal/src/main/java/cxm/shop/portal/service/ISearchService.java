package cxm.shop.portal.service;

import cxm.shop.portal.pojo.SearchResult;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月16日下午3:45:12
 */
public interface ISearchService {
	SearchResult search(String queryString ,int page);
}
