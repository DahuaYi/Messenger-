package com.dahua.messaging.controller;

import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dto.FriendInvitationDTO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.request.SendFriendInvitationRequest;
import com.dahua.messaging.response.FriendInvitationResponse;
import com.dahua.messaging.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    @Autowired private FriendService friendService;
    @Autowired private UserDAO userDAO;
    @PostMapping("/friends/sendInvitation")
    public void sendFriendInvitation(
            @CookieValue String loginToken,
            @RequestBody SendFriendInvitationRequest sendFriendInvitationRequest) {

        this.friendService.sendFriendInvitation(loginToken,
                                                sendFriendInvitationRequest.getReceiverUsername(),
                                                sendFriendInvitationRequest.getMessage());

    }


    @GetMapping("/friends/listPendingInvitations")
    public List<FriendInvitationResponse> listPendingInvitations(@CookieValue String loginToken) {
        List<FriendInvitationDTO> friendInvitationDTOs = this.friendService.listPendingInvitations(loginToken); //Get all the invitations the receiver has
        List<FriendInvitationResponse> friendInvitationResponses = new ArrayList<>();
        for (FriendInvitationDTO friendInvitationDTO : friendInvitationDTOs) { //loop all the invitations
            UserDTO receiver = this.userDAO.selectById(friendInvitationDTO.getReceiverUserId());
            UserDTO sender = this.userDAO.selectById(friendInvitationDTO.getSenderUserId());
            friendInvitationResponses.add(new FriendInvitationResponse(friendInvitationDTO, receiver, sender)); //adding more information personal for each invitation
        }
        return friendInvitationResponses;
    }

    @PostMapping("/friends/accept")
    public void accept(@CookieValue String loginToken, @RequestParam int friendInvitationId) {
        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);
        this.friendService.acceptFriend(userDTO, friendInvitationId);

    }

    @PostMapping("/friends/reject")
    public void reject(@CookieValue String loginToken, @RequestParam int friendInvitationId) {
        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);
        this.friendService.rejectFriend(userDTO, friendInvitationId);
    }

    @GetMapping("/friends/listFriends")
    public List<String> listFriends(@CookieValue String loginToken) {
        //select * from friend_invitation WHERE status == 'ACCEPTED' And (sender_user_id = me OR receiver_user_id = me)

        List<FriendInvitationDTO> friendInvitationDTOs = this.friendService.listFriends(loginToken);
        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);
         //Get all the invitations the receiver has
        List<String> friends = new ArrayList<>();
        for (FriendInvitationDTO friendInvitationDTO : friendInvitationDTOs) { //loop all the invitations
            UserDTO receiver = this.userDAO.selectById(friendInvitationDTO.getReceiverUserId());
            UserDTO sender = this.userDAO.selectById(friendInvitationDTO.getSenderUserId());
            if (receiver.getId() == userDTO.getId()) {
                friends.add(sender.getUsername());
            } else {
                friends.add(receiver.getUsername());
            }

        }
        return friends;
    }


}
