package com.dahua.messaging.request;

public class GroupInviteRequest {
    private int groupChatId;
    private int memberUserId;

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(String groupChatId) {
        this.groupChatId = Integer.parseInt(groupChatId);
    }

    public int getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(String memberUserId) {
        this.memberUserId = Integer.parseInt(memberUserId);
    }
}
