package com.viiup.web.flock.models;

import java.util.Date;

/**
 * Created by AbdullahMoyeen on 2/1/16.
 */
public class GroupModel {

    private int groupId;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;
    private String groupName;
    private String groupDescription;
    private int activeMemberCount;
    private int pendingMemberCount;
    private int upcomingEventCount;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getGroupName() {
        return groupName;
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

    public int getActiveMemberCount() {
        return activeMemberCount;
    }

    public void setActiveMemberCount(int activeMemberCount) {
        this.activeMemberCount = activeMemberCount;
    }

    public int getPendingMemberCount() {
        return pendingMemberCount;
    }

    public void setPendingMemberCount(int pendingMemberCount) {
        this.pendingMemberCount = pendingMemberCount;
    }

    public int getUpcomingEventCount() {
        return upcomingEventCount;
    }

    public void setUpcomingEventCount(int upcomingEventCount) {
        this.upcomingEventCount = upcomingEventCount;
    }
}