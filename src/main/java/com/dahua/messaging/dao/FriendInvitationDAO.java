package com.dahua.messaging.dao;

import com.dahua.messaging.dto.FriendInvitationDTO;
import com.dahua.messaging.enumeration.FriendInvitationStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendInvitationDAO {
    @Insert("INSERT INTO friend_invitation (sender_user_id, receiver_user_id, message, send_time, status) " +
            "VALUES (#{senderUserId}, #{receiverUserId}, #{message}, #{sendTime}, #{status})")
    void insert(FriendInvitationDTO friendInvitationDTO);


    @Select("SELECT * FROM friend_invitation WHERE receiver_user_id = #{receiverUserId}")
    List<FriendInvitationDTO> selectPendingInvitation(int receiverUserId);

    @Update("UPDATE friend_invitation SET status = 'ACCEPTED' WHERE id = #{friendInvitationId} AND receiver_user_id = #{id}")
    void accept(int id, int friendInvitationId);

    @Update("UPDATE friend_invitation SET status = 'REJECTED' WHERE id = #{friendInvitationId} AND receiver_user_id = #{id}")
    void reject(int id, int friendInvitationId);

    @Select("SELECT * FROM friend_invitation WHERE status = 'ACCEPTED' AND (sender_user_id = #{userId} OR receiver_user_id = #{userId})")
    List<FriendInvitationDTO> selectFriends(int userId);

    @Select("SELECT * FROM friend_invitation WHERE status = 'ACCEPTED' AND ((sender_user_id = #{memberId} AND receiver_user_id = #{userId}) OR (sender_user_id = #{userId} AND receiver_user_id = #{memberId}))")
    FriendInvitationDTO isFriend(int userId, int memberId);
}
