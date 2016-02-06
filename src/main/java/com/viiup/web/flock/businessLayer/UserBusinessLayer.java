package com.viiup.web.flock.businessLayer;

import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserRoleModel;
import com.viiup.web.flock.providers.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/6/16.
 */
@Service
public class UserBusinessLayer implements IUserBusinessLayer {

    @Autowired
    private IUserProvider userProvider;

    public UserModel getUserByEmailAddress(String emailAddress){

        UserModel user = userProvider.getUserByEmailAddress(emailAddress);

        return user;
    }

    public List<UserRoleModel> getUserRolesByUserId(int userId){

        List<UserRoleModel> userRoles = userProvider.getUserRolesByUserId(userId);

        return userRoles;
    }

    public UserModel getUserByUserId(int userId){

        UserModel user = userProvider.getUserByUserId(userId);

        user.setPassword(null);
        user.setSalt(null);

        return user;
    }
}