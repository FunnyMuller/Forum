package com.ricostone.communitymodule.Entity;

public class defineInformation {
   // 自定义的一些变量，其中 icon 、 background 、reply_css 都是路径
   private int communityId;
   private String icon;
   private String reply_css;
   private String background;

   public int getCommunityId() {
      return communityId;
   }

   public void setCommunityId(int communityId) {
      this.communityId = communityId;
   }

   public String getIcon() {
      return icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public String getReply_css() {
      return reply_css;
   }

   public void setReply_css(String reply_css) {
      this.reply_css = reply_css;
   }

   public String getBackground() {
      return background;
   }

   public void setBackground(String background) {
      this.background = background;
   }
}
