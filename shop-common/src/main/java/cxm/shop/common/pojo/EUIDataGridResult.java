/**
 * 
 */
package cxm.shop.common.pojo;

import java.util.List;

/**
 * 根据EasyUI的dataGrid列表返回数据
 * @author	xiaoman
 * @Date 2020年2月3日下午2:22:28
 */
public class EUIDataGridResult {
	private long total;
	private List<?> rows;
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	

}
