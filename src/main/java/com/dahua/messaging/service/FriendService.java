package com.dahua.messaging.service;

import com.dahua.messaging.dao.FriendInvitationDAO;
import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dto.FriendInvitationDTO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.enumeration.FriendInvitationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendService {

    @Autowired private UserDAO userDAO;
    @Autowired private FriendInvitationDAO friendInvitationDAO;
    public void sendFriendInvitation(String senderLoginToken,
                                     String receiverUsername,
                                     String message) {
        UserDTO sender = this.userDAO.selectByLoginToken(senderLoginToken);
        UserDTO receiver = this.userDAO.selectByUsername(receiverUsername);
        FriendInvitationDTO friendInvitationDTO = new FriendInvitationDTO();

        friendInvitationDTO.setSenderUserId(sender.getId());
        friendInvitationDTO.setReceiverUserId(receiver.getId());
        friendInvitationDTO.setMessage(message);
        friendInvitationDTO.setSendTime(new Date());
        friendInvitationDTO.setStatus(FriendInvitationStatus.PENDING);
        this.friendInvitationDAO.insert((friendInvitationDTO));


    }

    public List<FriendInvitationDTO> listPendingInvitations(String receiverLoginToken) {
        UserDTO receiver = this.userDAO.selectByLoginToken(receiverLoginToken);
        return this.friendInvitationDAO.selectPendingInvitation(receiver.getId());
    }

    public void acceptFriend(UserDTO userDTO, int friendInvitationId) {
        this.friendInvitationDAO.accept(userDTO.getId(), friendInvitationId);
    }

    public void rejectFriend(UserDTO userDTO, int friendInvitationId) {
        this.friendInvitationDAO.reject(userDTO.getId(),friendInvitationId);
    }

    public List<FriendInvitationDTO> listFriends(String loginToken) {
        UserDTO receiver = this.userDAO.selectByLoginToken(loginToken);
        return this.friendInvitationDAO.selectFriends(receiver.getId());
    }
}
