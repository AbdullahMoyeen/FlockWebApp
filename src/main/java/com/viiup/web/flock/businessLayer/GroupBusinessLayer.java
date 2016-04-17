package com.viiup.web.flock.businessLayer;

import com.viiup.web.flock.businessLayer.interfaces.IGroupBusinessLayer;
import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;
import com.viiup.web.flock.models.UserGroupModel;
import com.viiup.web.flock.providers.interfaces.IGroupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/7/16.
 */
@Service
public class GroupBusinessLayer implements IGroupBusinessLayer {

    @Autowired
    IGroupProvider groupProvider;

    @Override
    public List<GroupModel> getAdminGroupsByUserId(int userId) {
        return groupProvider.getAdminGroupsByUserId(userId);
    }

    @Override
    public List<UserGroupModel> getGroupsByUserId(int userId){ return groupProvider.getGroupsByUserId(userId);
    }

    @Override
    public boolean IsUserAdminOfGroup(int groupId, int userId) {
        return groupProvider.IsUserAdminOfGroup(groupId, userId);
    }

    @Override
    public GroupModel getGroupByGroupId(int groupId) {
        return groupProvider.getGroupByGroupId(groupId);
    }

    @Override
    public GroupModel updateGroup(GroupModel group) {
        return groupProvider.updateGroup(group);
    }

    @Override
    public List<EventModel> getGroupEventsByGroupId(int groupId) {
        return groupProvider.getGroupEventsByGroupId(groupId);
    }

    @Override
    public List<GroupUserModel> getGroupUsersByGroupId(int groupId) {
        return groupProvider.getGroupUsersByGroupId(groupId);
    }

    @Override
    public void approveGroupMembership(int groupId, int userId) {
        groupProvider.approveGroupMembership(groupId, userId);
    }

    @Override
    public void denyGroupMembership(int groupId, int userId) {
        groupProvider.denyGroupMembership(groupId, userId);
    }

    @Override
    public void setGroupMembership( int groupId,int userId, boolean isMember){
        if (isMember == true){
            GroupUserModel groupUser = new GroupUserModel();

            groupUser.setUserId(userId);
            groupUser.setGroupId(groupId);
            groupUser.setGroupMembershipStatus("P");

            groupProvider.insertGroupUser(groupUser);

        } else

            groupProvider.deleteGroupUser(userId, groupId);
    }
}
