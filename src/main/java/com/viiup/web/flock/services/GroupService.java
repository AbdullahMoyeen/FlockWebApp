package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.IGroupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */

@Service("groupService")
@Transactional
public class GroupService implements IGroupService {

    @Autowired
    IGroupProvider groupProvider;

    @Override
    public List<GroupModel> getAdminGroupsByUserId(int userId) {
        return groupProvider.getAdminGroupsByUserId(userId);
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
    public List<GroupUserModel> getGroupUsersByGroupId(int groupId) {
        return groupProvider.getGroupUsersByGroupId(groupId);
    }

    @Override
    public List<EventModel> getGroupEventsByGroupID(int groupId) {
        return groupProvider.getGroupEventsByGroupID(groupId);
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
    public List<Product> searchProducts(String searchString) {
        return groupProvider.searchProducts(searchString);
    }

    @Override
    public Product getProductByProductID(int productID) {
        return groupProvider.getProductByProductID(productID);
    }

    @Override
    public boolean updateInventory(int productID, int quantity) {
        return groupProvider.updateInventory(productID, quantity);
    }
}
