package com.ricostone.communitymodule.Util;

import org.springframework.stereotype.Component;

@Component
public class DefinitionHolder {
   private final ThreadLocal<String> icons = new ThreadLocal<>();
   private final ThreadLocal<String> reply_csss = new ThreadLocal<>();
   private final ThreadLocal<String> backgrounds = new ThreadLocal<>();

   public String getIcon() {
	  return icons.get();
   }

   public void setIcon(String icon) {
	  icons.set(icon);
   }

   public String getReply_css() {
	  return reply_csss.get();
   }

   public void setReply_css(String reply_css) {
	  reply_csss.set(reply_css);
   }

   public String getBackground() {
	  return backgrounds.get();
   }

   public void setBackground(String background) {
	  backgrounds.set(background);
   }

   public void clear() {
	  icons.remove();
	  reply_csss.remove();
	  backgrounds.remove();
   }
}
