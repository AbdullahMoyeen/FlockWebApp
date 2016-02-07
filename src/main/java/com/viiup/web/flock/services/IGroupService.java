package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
public interface IGroupService {

    List<GroupModel> getAdminGroupsByUserId(int userId);
    boolean IsUserAdminOfGroup(int groupId, int userId);
    GroupModel getGroupByGroupId(int groupId);
    GroupModel updateGroup(GroupModel group);
    List<EventModel> getGroupEventsByGroupId(int groupId);
    List<GroupUserModel> getGroupUsersByGroupId(int groupId);
    void approveGroupMembership(int groupId, int userId);
    void denyGroupMembership(int groupId, int userId);
}
