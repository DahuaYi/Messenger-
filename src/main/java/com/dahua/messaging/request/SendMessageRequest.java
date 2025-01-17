package com.dahua.messaging.request;

public class SendMessageRequest {
    private String content;
    private Integer receiverUserId;
    private Integer groupChatId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
