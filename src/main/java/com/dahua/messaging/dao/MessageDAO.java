package com.dahua.messaging.dao;

import com.dahua.messaging.dto.MessageDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageDAO {


    @Insert("INSERT INTO messages (senderUserId, receiverUserId, groupChatId, content, sendTime) " +
            "VALUES (#{senderUserId}, #{receiverUserId}, #{groupChatId}, #{content}, #{sendTime})")
    void insert(MessageDTO messageDTO);


    @Select("SELECT * FROM messages WHERE receiverUserId = #{id}")
    List<MessageDTO> getMessages(int id);

    @Select("SELECT * FROM messages WHERE groupChatId = #{groupChatId}")
    List<MessageDTO> getGroupMessages(int groupChatId);
}
