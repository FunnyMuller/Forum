package com.ricostone.communitymodule.Controller;

import com.ricostone.communitymodule.Entity.Message;
import com.ricostone.communitymodule.Entity.MessageListInfo;
import com.ricostone.communitymodule.Entity.User;
import com.ricostone.communitymodule.Service.Impl.messageImpl;
import com.ricostone.communitymodule.Service.Impl.messageListInfoImpl;
import com.ricostone.communitymodule.Service.Impl.userImpl;
import com.ricostone.communitymodule.Util.CommunityUtil;
import com.ricostone.communitymodule.Util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class messageController {
   @Autowired
   private messageListInfoImpl messageListInfoImpl;
   @Autowired
   private messageImpl messageImpl;
   @Autowired
   private HostHolder hostHolder;
   @Autowired
   private userImpl userImpl;

   /**
	* 这里是得到message列表的数据
	*
	* @param receiverId  接收的用户的id
	* @param model 用于渲染数据
	* @return 返回页面
	*/
   @RequestMapping(path = "/message/{receiverId}", method = RequestMethod.GET)
   public String message(@PathVariable("receiverId") int receiverId, Model model) {
	  List<MessageListInfo> messageListInfos = messageListInfoImpl.selectMessageByToId(receiverId);
	  model.addAttribute("messageListInfos", messageListInfos);
	  return "/message";
   }

   /**
	* 获取整个会话的全部消息
	* @param conversationId 会话的id
	* @return 返回JSON消息给前端
	*/
   @RequestMapping(path = "/message/getConversationDetail/{conversationId}", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<Map<String, Object>> messageDetail(@PathVariable("conversationId") String conversationId) {
	  List<Message> messages = messageImpl.getMessagesByConversationId(conversationId);
	  if(messages == null || messages.isEmpty()){
		 Map<String,Object> map = new HashMap<>();
		 return ResponseEntity.ok(map);
	  }
	  User receiver = userImpl.selectUserById(hostHolder.getUser().getUserId());
	  User sender = userImpl.selectUserById(messages.get(0).getSenderId());
	  Map<String,Object> map = new HashMap<>();
	  map.put("receiver",receiver);
	  map.put("sender",sender);
	  map.put("messages",messages);
	  return ResponseEntity.ok(map);
   }

   /**
	* 发送消息
	* @param content 发送的消息
	* @param senderName 接收的用户姓名
	* 插入新的内容到 messageListInfo 中
	* @return 结果, 1 失败， 0 成功
	*/
   @RequestMapping(path = "/message/sendMessage", method = RequestMethod.POST)
   @ResponseBody
   public String sendMessage(String content, String senderName) {
	  User sender = userImpl.selectUserByName(senderName);
	  if(sender == null){
		 return CommunityUtil.getJSONString(1, "发送失败，用户不存在");
	  }
	  Message message = new Message();
	  message.setCommunityId(hostHolder.getUser().getCommunityId());
	  message.setContent(content);
	  message.setSenderId(sender.getUserId());
	  message.setReceiverId(hostHolder.getUser().getUserId());
	  if(sender.getUserId() < hostHolder.getUser().getUserId()){
		 message.setConversationId(sender.getUserId() + "_" + hostHolder.getUser().getUserId());
	  }else{
		 message.setConversationId(hostHolder.getUser().getUserId() + "_" + sender.getUserId());
	  }
	  message.setStatus(0);
	  String simplyContent = content.substring(0, Math.min(content.length(), 10));
	  messageListInfoImpl.updateMessageContent(simplyContent, message.getConversationId());
	  messageImpl.insertMessage(message);
	  return CommunityUtil.getJSONString(0, "发送成功");
   }
}
