package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.interfaces.IBaseBusinessLayer;
import com.viiup.web.flock.providers.interfaces.IBaseProvider;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.interfaces.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
@Service
public class BaseService implements IBaseService {

    @Autowired
    IBaseBusinessLayer baseBusinessLayer;

    @Autowired
    IBaseProvider baseProvider;

    @Override
    public void signUp(UserModel user) throws Exception{
        baseBusinessLayer.signUp(user);
    }

    @Override
    public void resetPassword(String emailAddress) throws Exception{

        baseBusinessLayer.resetPassword(emailAddress);
    }
}
