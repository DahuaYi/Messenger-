package com.dahua.messaging.service;

import com.dahua.messaging.dao.GroupChatMemberDAO;
import com.dahua.messaging.dao.MessageDAO;
import com.dahua.messaging.dao.UserDAO;
import com.dahua.messaging.dto.GroupChatDTO;
import com.dahua.messaging.dto.GroupChatMemberDTO;
import com.dahua.messaging.dto.MessageDTO;
import com.dahua.messaging.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired private MessageDAO messageDAO;
    @Autowired private UserDAO userDAO;
    @Autowired private GroupChatMemberDAO groupChatMemberDAO;

    public void sendMessage(String loginToken, String content, Integer receiverUserId, Integer groupChatId) {
        UserDTO userDTO = this.userDAO.selectByLoginToken(loginToken);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderUserId(userDTO.getId());
        messageDTO.setReceiverUserId(receiverUserId);
        messageDTO.setGroupChatId(groupChatId);
        messageDTO.setContent(content);
        messageDTO.setSendTime(new Date());

        this.messageDAO.insert(messageDTO);


    }

    public List<MessageDTO> listMessages(String loginToken) {
        UserDTO receiver = this.userDAO.selectByLoginToken(loginToken);
        return this.messageDAO.getMessages(receiver.getId());
    }

    public List<List<MessageDTO>> listGroupMessages(String loginToken) {
        UserDTO receiver = this.userDAO.selectByLoginToken(loginToken);

        List<List<MessageDTO>> res = new ArrayList<>();
        List<GroupChatMemberDTO> groupChatMemberDTOStDTO = this.groupChatMemberDAO.selectByUserId(receiver.getId());

        for (GroupChatMemberDTO x : groupChatMemberDTOStDTO) {

            res.add(this.messageDAO.getGroupMessages(x.getGroupChatId()));

        }

        return res;
    }
}
