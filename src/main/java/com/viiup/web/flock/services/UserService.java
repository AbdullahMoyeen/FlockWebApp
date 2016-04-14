package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.interfaces.IUserBusinessLayer;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.interfaces.IUserProvider;
import com.viiup.web.flock.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    IUserProvider userProvider;

    @Autowired
    IUserBusinessLayer userBusinessLayer;

    @Override
    public UserModel getUserByUserId(int userId) {
        return userBusinessLayer.getUserByUserId(userId);
    }

    @Override
    public void changeUserPassword(UserPasswordChangeModel userPassword) throws Exception {
        userBusinessLayer.changeUserPassword(userPassword);
    }
}
