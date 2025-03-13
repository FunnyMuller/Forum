package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Util.CommunityUtil;
import com.ricostone.communitymodule.Util.FileUploadUtil;
import com.ricostone.communitymodule.Util.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class defineController {
   private FileUploadUtil fileUploadUtil;
   @Autowired
   private HostHolder hostHolder;
   private static final Logger LOGGER = LoggerFactory.getLogger(defineController.class);
   @RequestMapping(path="/define", method = RequestMethod.GET)
   public String define() {
	  return "/defineForum";
   }
   @RequestMapping(path="/define", method = RequestMethod.POST)
   @ResponseBody
   public String defineForum(File background, File icon, File cssType) {
	  if(background == null || icon == null || cssType == null) {
		 return CommunityUtil.getJSONString(1, "上传文件不能为空");
	  }
//	  String backgroundName = background.getName().substring(background.getName().lastIndexOf("."));
//	  String iconName = icon.getName().substring(icon.getName().lastIndexOf("."));
//	  String cssName = cssType.getName().substring(cssType.getName().lastIndexOf("."));
//	  if(!backgroundName.equals(".jpg") && !backgroundName.equals(".png")) {
//		 return CommunityUtil.getJSONString(1, "上传图片格式不正确");
//	  }
//	  if(!iconName.equals(".ico")) {
//		 return CommunityUtil.getJSONString(1, "上传图片格式不正确");
//	  }
//	  if(!cssName.equals(".css")) {
//		 return CommunityUtil.getJSONString(1, "上传图片格式不正确");
//	  }
	  int communityId = hostHolder.getUser().getCommunityId();
	  fileUploadUtil.uploadFile((MultipartFile) background, "D:\\Project\\community\\fileload\\background", background.getName(), communityId);
//	  fileUploadUtil.uploadFile((MultipartFile) icon, "D:\\Project\\community\\fileload\\icon", icon.getName(), communityId);
	  fileUploadUtil.uploadFile((MultipartFile) cssType, "D:\\Project\\community\\fileload\\css", cssType.getName(), communityId);

	  return CommunityUtil.getJSONString(0, "上传成功");
   }
}
