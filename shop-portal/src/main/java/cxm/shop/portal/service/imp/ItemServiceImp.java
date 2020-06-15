package cxm.shop.portal.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cxm.shop.common.utils.HttpClientUtil;
import cxm.shop.common.utils.JsonUtils;
import cxm.shop.common.utils.TaotaoResult;
import cxm.shop.pojo.TbItem;
import cxm.shop.pojo.TbItemDesc;
import cxm.shop.pojo.TbItemParamItem;
import cxm.shop.portal.pojo.Item;
import cxm.shop.portal.pojo.ItemInfo;
import cxm.shop.portal.service.IItemService;

/**
 * 商品信息管理Service
 * @author	xiaoman
 * @Date 2020年2月17日上午11:52:35
 */
@Service
public class ItemServiceImp implements IItemService{
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	//根据商品id,取商品基础信息
	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			//调用rest的服务查询商品基础信息
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			if(!StringUtils.isBlank(json)) {
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
				if(taotaoResult.getStatus()==200) {
					ItemInfo item = (ItemInfo) taotaoResult.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//根据商品id,查询商品描述
	@Override
	public String getItemDescById(Long itemId) {
		try {
			//调用rest的服务查询商品desc
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
			if(taotaoResult.getStatus()==200) {
				TbItemDesc itemDesc=(TbItemDesc) taotaoResult.getData();
				//取商品描述信息
				String result = itemDesc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//根据商品id,查询规格参数
	@Override
	public String getItemParam(Long itemId) {
		try {
			//调用rest的服务查询商品param
			String url=REST_BASE_URL+ITEM_PARAM_URL+itemId;
			String json = HttpClientUtil.doGet(url);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
			if(taotaoResult.getStatus()==200) {
				TbItemParamItem itemParam=(TbItemParamItem) taotaoResult.getData();
				//取商品描述信息
				String paramData = itemParam.getParamData();
				//生成html
				//把规格参数转换为java对象
				List<Map> jsonList=JsonUtils.jsonToList(paramData, Map.class);
				if(jsonList!=null && jsonList.size()>0) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "参数请求失败";
	}

}
