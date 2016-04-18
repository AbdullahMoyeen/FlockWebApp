package com.viiup.web.flock.services.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/1/2016.
 */
public interface IGroupService {

    List<GroupModel> getAdminGroupsByUserId(int userId);
    boolean IsUserAdminOfGroup(int groupId, int userId);
    GroupModel getGroupByGroupId(int groupId);
    GroupModel updateGroup(GroupModel group);
    List<EventModel> getGroupEventsByGroupId(int groupId);

    List<UserGroupModel> getGroupsByUserId(int userId);

    List<GroupUserModel> getGroupUsersByGroupId(int groupId);
    void approveGroupMembership(int groupId, int userId);
    void denyGroupMembership(int groupId, int userId);

    void setGroupMembership(int groupId, int userId, boolean isMember);

    List<RefGroupCategoryModel> getRefGroupCategoryList();
}
