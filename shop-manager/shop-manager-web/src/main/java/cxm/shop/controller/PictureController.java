package cxm.shop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cxm.shop.service.IPictureService;

/**
 *	上传图片处理	
 * @author	xiaoman
 * @Date 2020年2月7日下午12:40:28
 */
@Controller
public class PictureController {
	@Autowired
	private IPictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map pictureUpload(MultipartFile uploadFile) {
		Map result=null;
		try {
			result = pictureService.uploadPictur(uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("url:"+result.get("url"));;
		return result;
		
	}
}
