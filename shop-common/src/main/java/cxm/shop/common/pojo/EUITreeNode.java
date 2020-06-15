package cxm.shop.common.pojo;

/**
 *	根据EasyUI Tree控件节点,创建的pojo
 * @author	xiaoman
 * @Date 2020年2月3日下午3:47:53
 */
public class EUITreeNode {
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String text;
	private String state;
	
}
