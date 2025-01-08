package com.dahua.messaging.dto;

public class GroupChatMemberDTO { //insert into group_chat_member (chatid = 17, user = 13)
    private int id;
    private int groupChatId;
    private int memberUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(int groupChatId) {
        this.groupChatId = groupChatId;
    }

    public int getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(int memberUserId) {
        this.memberUserId = memberUserId;
    }
}

//select member_user_id from group_chat_member WHERE group_chat_id = 1
//select group_chat_id from group_chat_member WHERE member_user_id = 14
