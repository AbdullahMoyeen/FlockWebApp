package com.viiup.web.flock.providers.interfaces;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;
import com.viiup.web.flock.models.UserGroupModel;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/1/2016.
 */
public interface IGroupProvider {

    List<GroupModel> getAdminGroupsByUserId(int userId);

    List<UserGroupModel> getGroupsByUserId(int userId);

    boolean IsUserAdminOfGroup(int groupId, int userId);
    GroupModel getGroupByGroupId(int groupId);
    GroupModel updateGroup(GroupModel group);
    List<GroupUserModel> getGroupUsersByGroupId(int groupId);
    List<EventModel> getGroupEventsByGroupId(int groupId);
    void approveGroupMembership(int groupId, int userId);
    void denyGroupMembership(int groupId, int userId);

    void insertGroupUser(GroupUserModel groupUser);

    void deleteGroupUser(int userId, int groupId);
}
