package com.ricostone.communitymodule.Util;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
@Component
// 实现文件上传的工具类
public class FileUploadUtil {
   Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
   /**
	* 上传文件
	*
	* @param multipartFile 上传的文件
	* @param uploadPath    服务器上文件要上传的路径
	* @param fileName      服务里上要存储的文件的名字
	* @return boolean                上传成功返回true，否则返回false
	*/
   public boolean uploadFile(MultipartFile multipartFile, String uploadPath, String fileName, int communityId) {
	  File file = new File(uploadPath);
	  if (!file.exists()) {
		 boolean mkdirs = file.mkdirs();
		 if (!mkdirs) {
			return false;
		 }
	  }
	  InputStream inputStream = null;
	  FileOutputStream fileOutputStream = null;
	  try {
		 inputStream = multipartFile.getInputStream();
		 String suffix = fileName.substring(fileName.lastIndexOf("."));
		 String prefix = fileName.substring(0, fileName.lastIndexOf("."));
		 fileOutputStream = new FileOutputStream(uploadPath + prefix + "_" + communityId + suffix);
		 int len;
		 byte[] buffer = new byte[1024];
		 while ((len = inputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, len);
		 }
		 fileOutputStream.close();
		 return true;
	  } catch (IOException e) {
		 throw new RuntimeException(e);
	  } finally {
		 try {
			if (inputStream != null) {
			   inputStream.close();
			}
			if (fileOutputStream != null) {
			   fileOutputStream.close();
			}
		 } catch (IOException e) {
			throw new RuntimeException(e);
		 }
	  }
   }
}
