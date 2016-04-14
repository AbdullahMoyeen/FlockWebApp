package com.viiup.web.flock.services.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
public interface IUserService {

    UserModel getUserByUserId(int userId);
    void changeUserPassword(UserPasswordChangeModel userPassword) throws Exception;
}