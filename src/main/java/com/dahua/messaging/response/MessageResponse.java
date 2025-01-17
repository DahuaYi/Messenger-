package com.dahua.messaging.response;

import java.util.Date;

public class MessageResponse {
    private int id; //primary key
    private int senderUserId;
    private String senderUsername;
    private String message;
    private Date sendTime;

    public MessageResponse(int id, int senderUserId, String senderUsername, String message, Date sendTime) {
        this.id = id;
        this.senderUserId = senderUserId;
        this.senderUsername = senderUsername;
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

    @Override
    public String toString() {
        return "MessageResponse{" +
                "senderUserId=" + senderUserId +
                ", senderUsername='" + senderUsername + '\'' +
                ", message='" + message + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
