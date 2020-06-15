package cxm.shop.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传服务接口
 * @author	xiaoman
 * @Date 2020年2月6日下午8:32:44
 */
public interface IPictureService {
	Map uploadPictur(MultipartFile uploadFile) throws Exception;
}
