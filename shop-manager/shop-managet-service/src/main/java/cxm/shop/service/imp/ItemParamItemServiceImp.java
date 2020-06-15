package cxm.shop.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.JsonUtils;
import cxm.shop.mapper.TbItemParamItemMapper;
import cxm.shop.pojo.TbItemParamItem;
import cxm.shop.pojo.TbItemParamItemExample;
import cxm.shop.pojo.TbItemParamItemExample.Criteria;
import cxm.shop.service.IItemParamItemService;

/**
 *	商品与商品规格参数service接口
 * @author	xiaoman
 * @Date 2020年2月8日下午7:59:24
 */
@Service
public class ItemParamItemServiceImp implements IItemParamItemService{
	@Autowired
	private TbItemParamItemMapper itemParamItemmapper;
	//根据商品id，查询商品规格参数
	@Override
	public String getItemParamByItemId(Long itemId) throws Exception {
		TbItemParamItemExample example=new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemmapper.selectByExampleWithBLOBs(example);
		if(list==null && list.size()==0) {
			return "";
		}
		//处理tbItemParamItem
		TbItemParamItem tbItemParamItem = list.get(0);
		String paramData = tbItemParamItem.getParamData();
		//生成html
		//把规格参数转换为java对象
		List<Map> jsonList=JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb=new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class==\"Ptable\">\n");
		sb.append("<tbody>\n");
		for(Map map:jsonList) {
			sb.append("	<tr>\n");
			sb.append("		<th class=\"tdTitle\" colspan=\"2\" >"+map.get("group")+"</th>\n");
			sb.append("	</tr>\n");
			List<Map> paramList=(List<Map>) map.get("params");
			for(Map map2:paramList) {
				sb.append("	<tr>\n");
				sb.append("		<td class=\"tdTitle\" >"+map2.get("k")+"</td>\n");
				sb.append("		<td>"+map2.get("v")+"</td>\n");
				sb.append("	</tr>\n");
			}
		}
		sb.append("</tbody>\n");
		sb.append("</table> \n");
		return sb.toString();
	}

}
