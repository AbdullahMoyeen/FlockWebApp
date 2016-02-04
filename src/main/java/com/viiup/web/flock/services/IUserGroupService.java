package com.viiup.web.flock.services;

import com.viiup.web.flock.models.UserGroup;
import com.viiup.web.flock.models.UserGroup;

import java.util.List;

/**
 * Created by Niranjan on 2/1/2016.
 * This interface provides the CRUD operations to be carried out on
 * the user groups.
 */
public interface IUserGroupService {

    UserGroup findById(long id);

    void saveUserGroup(UserGroup userGroup);

    void updateUserGroup(UserGroup userGroup);

    void deleteUserGroupById(long id);

    List<UserGroup> findAllUserGroups();

    void deleteAllUserGroups();

    boolean isUserGroupExists(UserGroup userGroup);
}
