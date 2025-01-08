package com.dahua.messaging.service;

import com.dahua.messaging.dao.FriendInvitationDAO;
import com.dahua.messaging.dao.GroupChatDAO;
import com.dahua.messaging.dao.GroupChatMemberDAO;
import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dto.FriendInvitationDTO;
import com.dahua.messaging.dto.GroupChatDTO;
import com.dahua.messaging.dto.GroupChatMemberDTO;
import com.dahua.messaging.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupChatService {

    @Autowired private UserDAO userDAO;
    @Autowired private GroupChatDAO groupChatDAO;
    @Autowired private GroupChatMemberDAO groupChatMemberDAO;

    @Autowired private FriendInvitationDAO friendInvitationDAO;

    public void createGroupChat(String loginToken, String groupChatName, String broadCast) {
        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);
        GroupChatDTO groupChatDTO = new GroupChatDTO();
        groupChatDTO.setName(groupChatName);
        groupChatDTO.setBroadcast(broadCast);
        groupChatDTO.setCreatorUserId(userDTO.getId());
        groupChatDTO.setCreateTime(new Date());

        this.groupChatDAO.insert(groupChatDTO);

        GroupChatMemberDTO groupChatMemberDTO = new GroupChatMemberDTO();
        GroupChatDTO groupChat = this.groupChatDAO.selectByAll(groupChatName, broadCast, userDTO.getId());
        groupChatMemberDTO.setGroupChatId(groupChat.getId()); //groupchat ID
        groupChatMemberDTO.setMemberUserId(groupChatDTO.getCreatorUserId());

        this.groupChatMemberDAO.createAndInsert(groupChatMemberDTO);

    }


    public void addToGroupChat(String loginToken, int groupChatId, int memberUserId) throws Exception{

        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);

        GroupChatDTO groupChatDTO = this.groupChatMemberDAO.selectByBoth(groupChatId, userDTO.getId());

        if (groupChatDTO == null) { //check if the groupchat exist and if the user is in the groupchat
            throw new Exception("Group Chat doesn't exist or user not in the group Chat");
        }

        FriendInvitationDTO invitation = this.friendInvitationDAO.isFriend(userDTO.getId(), memberUserId);//check if user and invite member is friend

        if (invitation == null) {
            throw new Exception("The invited person is not in your friend list");
        }

        GroupChatMemberDTO groupChatMemberDTO = new GroupChatMemberDTO();

        groupChatMemberDTO.setGroupChatId(groupChatId); //groupchat ID
        groupChatMemberDTO.setMemberUserId(memberUserId);

        this.groupChatMemberDAO.createAndInsert(groupChatMemberDTO);

    }


}
