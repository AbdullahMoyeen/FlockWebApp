package com.viiup.web.flock.services;

import com.viiup.web.flock.providers.interfaces.IEmailProvider;
import com.viiup.web.flock.services.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
@Service
public class EmailService implements IEmailService {

    @Autowired
    IEmailProvider emailProvider;

    @Override
    public void sendEmail(String[] toAddresses, String subject, String messageText){

        emailProvider.sendEmail(toAddresses, subject, messageText);
    }
}