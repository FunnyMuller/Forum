package com.ricostone.communitymodule.Service;
import com.ricostone.communitymodule.Entity.defineInformation;
public interface defineInformationInterface {
   int insertDefineInformation(int communityId, String icon, String reply_css, String background);
   int updateDefineInformation(int communityId, String icon, String reply_css, String background);
   defineInformation selectDefineInformation(int communityId);
   int communityCount();
}
