package com.viiup.web.flock.businessLayer;

import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserPasswordChangeModel;
import com.viiup.web.flock.models.UserRoleModel;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/5/16.
 */
public interface IUserBusinessLayer {

    UserModel getUserByEmailAddress(String emailAddress);
    List<UserRoleModel> getUserRolesByUserId(int userId);
    UserModel getUserByUserId(int userId);
    void changeUserPassword(UserPasswordChangeModel userPassword) throws Exception;
}