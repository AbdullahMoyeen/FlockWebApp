package com.viiup.web.flock.models;

/**
 * Created by Niranjan on 1/28/2016.
 */
public class UserGroup {
    private long groupId;
    private String groupName;
    private String groupDescription;
    private long memberCount;

    public UserGroup() {
        this.groupId = 0;
    }

    public UserGroup(long groupId, String groupName, String groupDescription, long memberCount) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.memberCount = memberCount;
    }

    public long getUserGroupId() {
        return this.groupId;
    }

    public void setUserGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserGroup other = (UserGroup) obj;
        if (groupId != other.groupId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + groupId + ", Group Name=" + groupName + ", Description=" + groupDescription
                + ", Member count=" + memberCount + "]";
    }
}
