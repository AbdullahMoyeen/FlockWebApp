package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 1/27/2016.
 */
public interface IBaseService {

    void signUp(UserModel user) throws Exception;
    void resetPassword(String emailAddress) throws Exception;
}
