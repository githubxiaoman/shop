package cxm.shop.search.service.imp;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.ExceptionUtil;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.search.mapper.Itemmapper;
import cxm.shop.search.pojo.Item;
import cxm.shop.search.service.IItemService;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月14日下午1:47:28
 */
@Service
public class ItemServiceImp  implements IItemService{
	@Autowired
	private Itemmapper itemmapper;
	
	@Autowired
	private SolrServer solrServer;
	@Override
	public TaotaoResult inputAllItems() throws Exception {
		try {
			//查询商品列表
			List<Item> list = itemmapper.getItemList();
			//把商品信息写入索引库
			for (Item item : list) {
				//创建一个solrInputDocument对象
				SolrInputDocument input=new SolrInputDocument();
				input.addField("id", item.getId());
				input.addField("item_title", item.getTitle());
				input.addField("item_sell_point", item.getSell_point());
				input.addField("item_price", item.getPrice());
				input.addField("item_image", item.getImage());
				input.addField("item_category_name", item.getCategory_name());
				input.addField("item_desc", item.getItem_desc());
				//写入索引库
				solrServer.add(input);
			}
			//提交
			solrServer.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
