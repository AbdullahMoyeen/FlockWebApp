package com.viiup.web.flock.services.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public interface IBaseService {

    void signUp(UserModel user) throws Exception;
    UserModel signIn(String emailAddress, String password) throws Exception;
    void resetPassword(String emailAddress) throws Exception;
}
