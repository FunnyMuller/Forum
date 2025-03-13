package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.defineInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface defineInformationMapper {
   int insertDefineInformation(int communityId, String icon, String reply_css, String background);

   int updateDefineInformation(int communityId, String icon, String reply_css, String background);

   defineInformation selectDefineInformation(int communityId);
   int communityCount();
}
