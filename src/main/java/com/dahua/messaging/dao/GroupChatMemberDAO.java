package com.dahua.messaging.dao;

import com.dahua.messaging.dto.GroupChatDTO;
import com.dahua.messaging.dto.GroupChatMemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupChatMemberDAO {

    @Insert("INSERT INTO group_chat_member (group_chat_id, member_user_id)" +
            "VALUES (#{groupChatId}, #{memberUserId})")
    void createAndInsert(GroupChatMemberDTO groupChatDTO);

    @Select("SELECT * FROM group_chat_member WHERE group_chat_id = #{groupChatId} AND member_user_id = #{id}")
    GroupChatDTO selectByBoth(int groupChatId, int id);

    @Select("SELECT * FROM group_chat_member WHERE member_user_id = #{id}")
    List<GroupChatMemberDTO> selectByUserId(int id);
}
