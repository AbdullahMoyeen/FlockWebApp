package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
public interface IBaseService {

    void signUp(UserModel user) throws Exception;
    void resetPassword(String emailAddress) throws Exception;
}
