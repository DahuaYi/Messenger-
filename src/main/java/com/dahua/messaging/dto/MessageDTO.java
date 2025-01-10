package com.dahua.messaging.dto;

import java.util.Date;

public class MessageDTO {
    private int id;
    private int senderUserId;
    private Integer receiverUserId;
    private Integer groupChatId;

    private String content;

    private Date sendTime;

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

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Integer receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(Integer groupChatId) {
        this.groupChatId = groupChatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
