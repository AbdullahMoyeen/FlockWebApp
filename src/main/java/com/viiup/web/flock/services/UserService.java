package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.IUserBusinessLayer;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
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
