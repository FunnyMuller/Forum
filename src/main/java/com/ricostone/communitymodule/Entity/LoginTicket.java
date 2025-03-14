package com.ricostone.communitymodule.Entity;

import java.util.Date;

/**
 * @author RicoStone
 * @date 2023/12/6
 */
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private Date expired;
    private int status;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
