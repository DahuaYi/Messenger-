package com.dahua.messaging.controller;

import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dto.MessageDTO;
import com.dahua.messaging.dto.UserDTO;
import com.dahua.messaging.request.SendMessageRequest;
import com.dahua.messaging.response.GroupMessageResponse;
import com.dahua.messaging.response.MessageResponse;
import com.dahua.messaging.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {

    @Autowired MessageService messageService;
    @Autowired UserDAO userDAO;
    @PostMapping("/send")
    public void sendMessage(@CookieValue String loginToken, @RequestBody SendMessageRequest sendMessageRequest) throws Exception {
        this.messageService.sendMessage(loginToken,
                                        sendMessageRequest.getContent(),
                                        sendMessageRequest.getReceiverUserId(),
                                        sendMessageRequest.getGroupChatId());
    }


    @GetMapping("message/list")
    public List<MessageResponse> listMessages(@CookieValue String loginToken) {
        List<MessageDTO> messages = this.messageService.listMessages(loginToken);
        List<MessageResponse> res = new ArrayList<>();

        for (MessageDTO i : messages) {
            UserDTO userDTO = this.userDAO.selectById(i.getSenderUserId());
            MessageResponse x = new MessageResponse(i.getId(), userDTO.getId(), userDTO.getUsername(), i.getContent(), i.getSendTime());
            res.add(x);
        }

        return res;
    }

    @GetMapping("message/groupChatMessages")
    public List<GroupMessageResponse> listGroupChatMessages(@CookieValue String loginToken) {
        List<List<MessageDTO>> messages = this.messageService.listGroupMessages(loginToken);
        List<GroupMessageResponse> res = new ArrayList<>();

        for (List<MessageDTO> innerList : messages) { // Outer loop to iterate over the lists
            for (MessageDTO i : innerList) { // Inner loop to iterate over individual MessageDTO objects
                UserDTO userDTO = this.userDAO.selectById(i.getSenderUserId());
                GroupMessageResponse x = new GroupMessageResponse(
                        i.getId(),
                        userDTO.getId(),
                        userDTO.getUsername(),
                        i.getGroupChatId(),
                        i.getContent(),
                        i.getSendTime()
                );
                res.add(x);
            }
        }

        return res;

    }
}
