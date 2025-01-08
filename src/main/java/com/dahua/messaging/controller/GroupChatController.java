package com.dahua.messaging.controller;

import com.dahua.messaging.dao.GroupChatMemberDAO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.request.GroupChatInformationRequest;
import com.dahua.messaging.request.GroupInviteRequest;
import com.dahua.messaging.request.RegisterUserRequest;
import com.dahua.messaging.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupChatController {

    @Autowired GroupChatService groupChatService;
    @Autowired GroupChatMemberDAO groupChatMemberDAO;

    @PostMapping("/groupChat/create")
    public void createGroupChat(@CookieValue String loginToken, @RequestBody GroupChatInformationRequest groupChatInformationRequest ) {
        this.groupChatService.createGroupChat(loginToken, groupChatInformationRequest.getGroupChatName(), groupChatInformationRequest.getBroadCast());
    }

    @PostMapping("/groupChat/invite")
    public void invite(@CookieValue String loginToken, @RequestBody GroupInviteRequest groupInviteRequest) throws Exception {
        this.groupChatService.addToGroupChat(loginToken, groupInviteRequest.getGroupChatId(), groupInviteRequest.getMemberUserId());
    }


}
