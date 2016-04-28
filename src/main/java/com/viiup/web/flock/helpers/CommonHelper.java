package com.viiup.web.flock.helpers;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by AbdullahMoyeen on 4/28/16.
 */
public class CommonHelper {

    private static final String fmt_email_domain = "@utdallas.edu";

    public static boolean isEmailValid(String email) {

        boolean isValid = true;

        if (!EmailValidator.getInstance().isValid((email)))
            isValid = false;
        if (email.replace(fmt_email_domain, "").trim().equals(""))
            isValid = false;

        return isValid;
    }

    public static boolean isDomainValid(String email) {
        return email.substring(email.indexOf("@")).equals(fmt_email_domain);
    }
}
