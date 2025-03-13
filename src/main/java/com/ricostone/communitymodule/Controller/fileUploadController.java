package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Service.Impl.defineInformationImpl;
import com.ricostone.communitymodule.Util.CommunityUtil;
import com.ricostone.communitymodule.Util.FileUploadUtil;
import com.ricostone.communitymodule.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ricostone.communitymodule.Entity.defineInformation;
/**
 * @author RicoStone
 * @date 2023/12/7
 */
@Controller
public class fileUploadController {
   public static final String ICON_UPLOAD_PATH = "D:/Project/CommunityModule/src/main/resources/static/icon/";
   public static final String REPLY_CSS_UPLOAD_PATH = "D:/Project/CommunityModule/src/main/resources/static/reply_css/";
   public static final String BACKGROUND_UPLOAD_PATH = "D:/Project/CommunityModule/src/main/resources/static/background/";
   @Autowired
   private defineInformationImpl defineInformationImpl;
   private final FileUploadUtil fileUploaderUtil = new FileUploadUtil();
   @Autowired
   private HostHolder hostHolder;

   @RequestMapping(path = "/getDefineInformation", method = RequestMethod.POST)
   @ResponseBody
   public String getDefineInformation(MultipartFile icon, MultipartFile reply_css, MultipartFile background) {
	  int communityId = hostHolder.getUser().getCommunityId();
	  fileUploaderUtil.uploadFile(icon, ICON_UPLOAD_PATH, icon.getOriginalFilename(), communityId);
	  fileUploaderUtil.uploadFile(reply_css, REPLY_CSS_UPLOAD_PATH, reply_css.getOriginalFilename(), communityId);
	  fileUploaderUtil.uploadFile(background, BACKGROUND_UPLOAD_PATH, background.getOriginalFilename(), communityId);
	  defineInformation information = defineInformationImpl.selectDefineInformation(communityId);
	  if(information != null) {
		 defineInformationImpl.updateDefineInformation(communityId, ICON_UPLOAD_PATH, REPLY_CSS_UPLOAD_PATH, BACKGROUND_UPLOAD_PATH);
	  } else {
	  	 defineInformationImpl.insertDefineInformation(hostHolder.getUser().getCommunityId(), ICON_UPLOAD_PATH, REPLY_CSS_UPLOAD_PATH, BACKGROUND_UPLOAD_PATH);
	  }
	  return CommunityUtil.getJSONString(0, "上传成功");
   }
}
