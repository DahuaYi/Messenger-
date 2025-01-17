package com.dahua.messaging.response;

import java.util.Date;

public class GroupMessageResponse {

    private int id; //primary key
    private int senderUserId;
    private String senderUsername;

    private int groupId;
    private String message;
    private Date sendTime;

    public GroupMessageResponse(int id, int senderUserId, String senderUsername, int groupId, String message, Date sendTime) {
        this.id = id;
        this.senderUserId = senderUserId;
        this.senderUsername = senderUsername;
        this.groupId = groupId;
        this.message = message;
        this.sendTime = sendTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
