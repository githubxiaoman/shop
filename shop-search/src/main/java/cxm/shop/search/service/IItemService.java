package cxm.shop.search.service;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

import cxm.shop.common.utils.TaotaoResult;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月14日下午1:46:19
 */
public interface IItemService {
     TaotaoResult inputAllItems()throws Exception;
}
