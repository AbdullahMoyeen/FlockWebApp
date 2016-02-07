package com.viiup.web.flock.providers;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
public interface IUserProvider {

    UserModel insertUser(UserModel user);
    UserRoleModel insertUserRole(UserRoleModel userRole);
    UserModel getUserByEmailAddress(String emailAddress);
    List<UserRoleModel> getUserRolesByUserId(int userId);
    UserModel getUserByUserId(int userId);
    boolean isCurrentPasswordValid(UserPasswordChangeModel userPassword);
    void updateUserPassword(UserPasswordChangeModel userPassword);
    void updateUserPassword(UserPasswordChangeModel userPassword, boolean expirePassword);
}
