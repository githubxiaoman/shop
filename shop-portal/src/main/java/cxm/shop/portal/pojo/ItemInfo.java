package cxm.shop.portal.pojo;

import cxm.shop.pojo.TbItem;

/**
 *
 * @author	xiaoman
 * @Date 2020年2月18日下午1:21:03
 */
public class ItemInfo extends TbItem{
	public String[] getImages() {
		String image=getImage();
		if(image!=null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
		
	}
}
