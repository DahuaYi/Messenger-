package com.dahua.messaging.response;

import com.dahua.messaging.dto.FriendInvitationDTO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.enumeration.FriendInvitationStatus;

import java.util.Date;

public class FriendInvitationResponse {
    private int id; //primary key
    private int senderUserId;
    private String senderUsername;
    private String senderNickname;
    private String senderEmail;

    private String receiverUsername;
    private String receiverNickname;
    private String receiverEmail;

    private int receiverUserId;
    private String message;
    private Date sendTime;

    private FriendInvitationStatus status;

    public FriendInvitationResponse(FriendInvitationDTO friendInvitationDTO,
                                    UserDTO receiver,
                                    UserDTO sender) {

        this.id = friendInvitationDTO.getId();

        this.senderUserId = friendInvitationDTO.getSenderUserId();
        this.senderUsername = sender.getUsername();
        this.senderNickname = sender.getNickname();
        this.senderEmail = sender.getEmail();

        this.receiverUserId = friendInvitationDTO.getReceiverUserId();
        this.receiverUsername = receiver.getUsername();
        this.receiverNickname = receiver.getNickname();
        this.receiverEmail = receiver.getEmail();

        this.message = friendInvitationDTO.getMessage();
        this.sendTime = friendInvitationDTO.getSendTime();
        this.status = friendInvitationDTO.getStatus();


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

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
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
