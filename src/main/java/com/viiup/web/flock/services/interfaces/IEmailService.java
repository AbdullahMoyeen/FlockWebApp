package com.viiup.web.flock.services.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public interface IEmailService {

    void sendEmail(String[] toAddresses, String subject, String messageText);
}