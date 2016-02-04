package com.viiup.web.flock.providers;

import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;
import com.viiup.web.flock.models.Product;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
public interface IGroupProvider {

    List<GroupModel> getAdminGroupsByUserId(int userId);
    boolean IsUserAdminOfGroup(int groupId, int userId);
    GroupModel getGroupByGroupId(int groupId);
    GroupModel updateGroup(GroupModel group);
    List<GroupUserModel> getGroupUsersByGroupId(int groupId);
    void approveGroupMembership(int groupId, int userId);
    void denyGroupMembership(int groupId, int userId);



    List<Product> searchProducts(String searchString);
    Product getProductByProductID(int productID);
    boolean updateInventory(int productID, int quantity);
}
