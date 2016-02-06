package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.IBaseProvider;
import com.viiup.web.flock.providers.IEmailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
@Service
public class EmailService implements IEmailService {

    @Autowired
    IEmailProvider emailProvider;

    @Override
    public void sendEmail(){

        emailProvider.sendEmail();
    }
}