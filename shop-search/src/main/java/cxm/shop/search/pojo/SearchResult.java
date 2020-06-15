package cxm.shop.search.pojo;

import java.util.List;

/**
 *	solr查询返回结果pojo
 * @author	xiaoman
 * @Date 2020年2月14日下午2:55:51
 */
public class SearchResult {
	//商品列表
	private List<Item> itemList;
	//总记录数
	private long recordCount;
	//总页数
	private long pageCount;
	//当前页
	private long curpage;
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurpage() {
		return curpage;
	}
	public void setCurpage(long curpage) {
		this.curpage = curpage;
	}
	
}
