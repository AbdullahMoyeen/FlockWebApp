package com.viiup.web.flock.businessLayer.interfaces;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/5/16.
 */
public interface IGroupBusinessLayer {

    List<GroupModel> getAdminGroupsByUserId(int userId);
    boolean IsUserAdminOfGroup(int groupId, int userId);
    GroupModel getGroupByGroupId(int groupId);
    GroupModel updateGroup(GroupModel group);
    List<EventModel> getGroupEventsByGroupId(int groupId);
    List<GroupUserModel> getGroupUsersByGroupId(int groupId);
    void approveGroupMembership(int groupId, int userId);
    void denyGroupMembership(int groupId, int userId);
}