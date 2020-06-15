package cxm.shop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cxm.shop.portal.service.IContentService;

/**
 *	页面controller
 * @author	xiaoman
 * @Date 2020年2月9日下午5:49:54
 */
@Controller
public class IndexController {
	@Autowired
	private IContentService contentService;
	
	//首页展示
	@RequestMapping("/index")
	public String showIndex(Model model) {
		try {
			String adJson = contentService.getContentList();
			model.addAttribute("ad1", adJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
}
