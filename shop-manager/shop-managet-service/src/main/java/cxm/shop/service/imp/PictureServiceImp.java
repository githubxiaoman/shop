package cxm.shop.service.imp;

import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cxm.shop.common.utils.IDUtils;
import cxm.shop.common.utils.SSHRemoteCall;
import cxm.shop.service.IPictureService;

/**
 *图片上传服务实现类
 * @author	xiaoman
 * @Date 2020年2月6日下午8:34:38
 */
@Service
public class PictureServiceImp implements IPictureService {
	/**
	 * 扫描resoure文件下的ftp.properties文件,通过@value方式获取
	 */
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;//ip
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;//接口
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;//用户名

	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;//密码

	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;//根路径
	
	@Value("${FTP_Visit_PATH}")
	private String FTP_Visit_PATH;//根路径
	
	@Override
	public Map uploadPictur(MultipartFile uploadFile)  {
		Map resultMap=new HashMap<>();
		try {
			//生成一个新的文件名 不唯一
			//提取原文件
			String oldName = uploadFile.getOriginalFilename();
			//生成新的文件名
			String newName = IDUtils.getImageName();
			newName=newName+oldName.substring(oldName.lastIndexOf("."));
			SSHRemoteCall call = SSHRemoteCall.getInstance2(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH);
			//设置session值
			call.sshRemoteCallLogin();
			String directory=FTP_BASE_PATH+newName;
			boolean result = call.uploadFile(uploadFile.getInputStream(), directory);
			String http=FTP_Visit_PATH+newName;
			if(!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				
			}else {
				resultMap.put("error", 0);
				resultMap.put("url",http);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
		
	}

}
