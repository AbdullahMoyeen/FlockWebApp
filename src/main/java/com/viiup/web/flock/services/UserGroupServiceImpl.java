package com.viiup.web.flock.services;

import com.viiup.web.flock.models.UserGroup;
import com.viiup.web.flock.models.UserGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Niranjan on 2/1/2016.
 */

@Service("userGroupService")
@Transactional
public class UserGroupServiceImpl implements IUserGroupService {
    private static final AtomicLong counter = new AtomicLong();
    private static List<UserGroup> userGroups;

    static {
        userGroups = populateDummyUsers();
    }

    // Dummy user group population for testing. Will be replaced by DB calls
    private static List<UserGroup> populateDummyUsers() {
        List<UserGroup> userGroups = new ArrayList<UserGroup>();
        userGroups.add(new UserGroup(counter.incrementAndGet(), "Group1", "This is group 1", 10));
        userGroups.add(new UserGroup(counter.incrementAndGet(), "Group2", "This is group 2", 15));
        userGroups.add(new UserGroup(counter.incrementAndGet(), "Group3", "This is group 3", 20));
        userGroups.add(new UserGroup(counter.incrementAndGet(), "Group4", "This is group 4", 2));
        userGroups.add(new UserGroup(counter.incrementAndGet(), "Group5", "This is group 5", 5));
        return userGroups;
    }

    @Override
    public UserGroup findById(long id) {
        for (UserGroup userGroup : userGroups) {
            if (userGroup.getUserGroupId() == id) {
                return userGroup;
            }
        }
        return null;
    }

    @Override
    public void saveUserGroup(UserGroup userGroup) {
        userGroup.setUserGroupId(counter.incrementAndGet());
        userGroups.add(userGroup);
    }

    @Override
    public void updateUserGroup(UserGroup userGroup) {
        int idx = userGroups.indexOf(userGroup);
        if (idx >= 0) userGroups.set(idx, userGroup);
    }

    @Override
    public void deleteUserGroupById(long id) {
        for (Iterator<UserGroup> iterator = userGroups.iterator(); iterator.hasNext(); ) {
            UserGroup user = iterator.next();
            if (user.getUserGroupId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<UserGroup> findAllUserGroups() {
        return userGroups;
    }

    @Override
    public void deleteAllUserGroups() {
        userGroups.clear();
    }

    @Override
    public boolean isUserGroupExists(UserGroup userGroup) {
        return findById(userGroup.getUserGroupId()) != null;
    }
}
