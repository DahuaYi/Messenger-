package com.dahua.messaging.dto;

import java.util.Date;
import java.util.List;

public class GroupChatDTO { //insert into group_chat (name, broadCast, creator_user_id, create_time) values('chat1'
    private int id;
    private String name;
    private String broadcast;
    private int creatorUserId;
    private Date createTime;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public int getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
