package com.dahua.messaging.dao;

import com.dahua.messaging.dto.GroupChatDTO;
import com.dahua.messaging.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GroupChatDAO {


    @Insert("INSERT INTO group_chat (name, broadcast, creator_user_id, create_time)" +
            "VALUES (#{name}, #{broadcast}, #{creatorUserId}, #{createTime})")
    void insert(GroupChatDTO groupChatDTO);

    @Select("SELECT * FROM group_chat WHERE name = #{name} AND broadcast = #{broadcast} AND creator_user_id = #{creatorId}")
    GroupChatDTO selectByAll(String name, String broadcast, int creatorId);
}
