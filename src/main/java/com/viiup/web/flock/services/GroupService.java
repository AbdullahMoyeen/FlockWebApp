package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.interfaces.IGroupBusinessLayer;
import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;
import com.viiup.web.flock.services.interfaces.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/1/2016.
 */

@Service("groupService")
@Transactional
public class GroupService implements IGroupService {

    @Autowired
    IGroupBusinessLayer groupBusinessLayer;

    @Override
    public List<GroupModel> getAdminGroupsByUserId(int userId) {
        return groupBusinessLayer.getAdminGroupsByUserId(userId);
    }

    @Override
    public boolean IsUserAdminOfGroup(int groupId, int userId) {
        return groupBusinessLayer.IsUserAdminOfGroup(groupId, userId);
    }

    @Override
    public GroupModel getGroupByGroupId(int groupId) {
        return groupBusinessLayer.getGroupByGroupId(groupId);
    }

    @Override
    public GroupModel updateGroup(GroupModel group) {
        return groupBusinessLayer.updateGroup(group);
    }

    @Override
    public List<EventModel> getGroupEventsByGroupId(int groupId) {
        return groupBusinessLayer.getGroupEventsByGroupId(groupId);
    }

    @Override
    public List<GroupUserModel> getGroupUsersByGroupId(int groupId) {
        return groupBusinessLayer.getGroupUsersByGroupId(groupId);
    }

    @Override
    public void approveGroupMembership(int groupId, int userId) {
        groupBusinessLayer.approveGroupMembership(groupId, userId);
    }

    @Override
    public void denyGroupMembership(int groupId, int userId) {
        groupBusinessLayer.denyGroupMembership(groupId, userId);
    }
}
