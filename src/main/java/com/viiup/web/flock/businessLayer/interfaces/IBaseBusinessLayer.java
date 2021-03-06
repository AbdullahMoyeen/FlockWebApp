package com.viiup.web.flock.businessLayer.interfaces;

import com.viiup.web.flock.models.UserModel;

/**
 * Created by AbdullahMoyeen on 2/6/16.
 */
public interface IBaseBusinessLayer {

    void signUp(UserModel user) throws Exception;
    UserModel signIn(String emailAddress, String password) throws Exception;
    void resetPassword(String emailAddress) throws Exception;
}
