package com.dahua.messaging.dto;

import com.dahua.messaging.enumeration.FriendInvitationStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class FriendInvitationDTO {

    private int id; //primary key
    private int senderUserId;
    private int receiverUserId;
    private String message;
    private Date sendTime;

    private FriendInvitationStatus status;

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

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
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

    public FriendInvitationStatus getStatus() {
        return status;
    }

    public void setStatus(FriendInvitationStatus status) {
        this.status = status;
    }
}
