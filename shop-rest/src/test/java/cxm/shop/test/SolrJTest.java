package cxm.shop.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * solrj的使用
 * @author	xiaoman
 * @Date 2020年2月14日下午12:24:48
 */
public class SolrJTest {
	//添加文档
	public  void addDocumentTest() throws SolrServerException, IOException{
		//创建一连接
		SolrServer servier=new HttpSolrServer("http://192.168.43.88:8080/solr/");
		//创建文档对象
		SolrInputDocument input=new SolrInputDocument();
		input.addField("id","test01");
		input.addField("item_title","测试001");
		//把文档对象写入文档库
		servier.add(input);
		//提交
		servier.commit();
	}
	//删除文档
	public  void deleteDocument() throws SolrServerException, IOException{
		//创建一连接
		SolrServer servier=new HttpSolrServer("http://192.168.43.88:8080/solr/");
		servier.deleteById("test01");
		servier.commit();
	}
	//查询文档
	public void queryDocument() throws Exception{
		SolrServer servier=new HttpSolrServer("http://192.168.43.88:8080/solr/");
		//创建查询对象
		SolrQuery query=new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		query.setStart(21);
		query.setRows(30);
		//执行查询
		QueryResponse response = servier.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("共查询到:"+solrDocumentList.getNumFound());
		
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			
		}
		
		
	}
}
