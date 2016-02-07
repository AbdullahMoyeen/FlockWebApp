package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.IBaseBusinessLayer;
import com.viiup.web.flock.providers.IBaseProvider;
import com.viiup.web.flock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
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
