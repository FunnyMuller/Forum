package com.ricostone.communitymodule.Entity;

public class MessageListInfo {
   private String conversationId;
   private String content;
   private String senderUsername;
   private String senderImageUrl;
   private int receiverId;
   private int status;

   public int getStatus() {
	  return status;
   }

   public void setStatus(int status) {
	  this.status = status;
   }

   public String getConversationId() {
	  return conversationId;
   }

   public void setConversationId(String conversationId) {
	  this.conversationId = conversationId;
   }

   public String getContent() {
	  return content;
   }

   public void setContent(String content) {
	  this.content = content;
   }

   public String getSenderUsername() {
	  return senderUsername;
   }

   public void setSenderUsername(String senderUsername) {
	  this.senderUsername = senderUsername;
   }

   public String getSenderImageUrl() {
	  return senderImageUrl;
   }

   public void setSenderImageUrl(String senderImageUrl) {
	  this.senderImageUrl = senderImageUrl;
   }

   public int getReceiverId() {
	  return receiverId;
   }

   public void setReceiverId(int receiverId) {
	  this.receiverId = receiverId;
   }
}
