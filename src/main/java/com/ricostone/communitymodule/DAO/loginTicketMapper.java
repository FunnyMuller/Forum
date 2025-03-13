package com.ricostone.communitymodule.DAO;

import com.ricostone.communitymodule.Entity.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author RicoStone
 * @date 2023/12/6
 */
@Mapper
public interface loginTicketMapper {
    int insertLoginTicket(LoginTicket loginTicket);
    LoginTicket selectByTicket(String ticket);
    int updateStatus(String ticket, int status);
}
