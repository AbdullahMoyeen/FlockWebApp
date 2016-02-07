package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
public interface IUserService {

    UserModel getUserByUserId(int userId);
    void changeUserPassword(UserPasswordChangeModel userPassword) throws Exception;
}