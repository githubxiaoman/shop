package cxm.shop.search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;

/**
 * solr集群测试
 * @author	xiaoman
 * @Date 2020年2月27日下午8:57:07
 */
public class SolrCloudTest {
	
	public void testSolrCloud() throws Exception{
		//zkHost就是zookeeper的地址列表使用逗号分隔
		String zkHost="192.168.43.88:2181,192.168.43.88:2182,192.168.43.88:2183";
		//创建solr集群连接
		CloudSolrServer solrServer=new CloudSolrServer(zkHost);
		//设置默认collection
		solrServer.setDefaultCollection("collection2");
		//创建一个文档对象
		SolrInputDocument document=new SolrInputDocument();
		//向文档添加域
		document.addField("id", "xiaoman001");
		document.addField("item_title", "测试商品1");
		//将文档添加到索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	public void deleteDocument() throws SolrServerException, IOException {
		String zkHost="192.168.43.88:2181,192.168.43.88:2182,192.168.43.88:2183";
		//创建solr集群连接
		CloudSolrServer solrServer=new CloudSolrServer(zkHost);
		//设置默认collection
		solrServer.setDefaultCollection("collection2");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
}
