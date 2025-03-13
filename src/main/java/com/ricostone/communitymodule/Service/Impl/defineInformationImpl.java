package com.ricostone.communitymodule.Service.Impl;

import com.ricostone.communitymodule.DAO.defineInformationMapper;
import com.ricostone.communitymodule.Entity.defineInformation;
import com.ricostone.communitymodule.Service.defineInformationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class defineInformationImpl implements defineInformationInterface {
	@Autowired
	private defineInformationMapper defineInformationMapper;
   @Override
   public int insertDefineInformation(@RequestParam("CommunityId") int communityId, String icon, String reply_css, String background) {
	  return defineInformationMapper.insertDefineInformation(communityId, icon, reply_css, background);
   }

   @Override
   public int updateDefineInformation(@RequestParam("CommunityId") int communityId, String icon, String reply_css, String background) {
	  return defineInformationMapper.updateDefineInformation(communityId, icon, reply_css, background);
   }

   @Override
   public defineInformation selectDefineInformation(int communityId) {
	  return defineInformationMapper.selectDefineInformation(communityId);
   }

   @Override
   public int communityCount() {
	  return defineInformationMapper.communityCount();
   }
}
