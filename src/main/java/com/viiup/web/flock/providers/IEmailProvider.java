package com.viiup.web.flock.providers;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 1/27/2016.
 */
public interface IEmailProvider {

    void sendEmail(String[] toAddresses, String subject, String messageText);
}
