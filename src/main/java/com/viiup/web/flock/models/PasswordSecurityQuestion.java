package com.viiup.web.flock.models;

/**
 * Created by amoyeen on 2/23/15.
 */
public class PasswordSecurityQuestion {

    private int securityQuestionID;
    private String securityQuestionText;

    public int getSecurityQuestionID(){
        return securityQuestionID;
    }
    public void setSecurityQuestionID(int securityQuestionID) {
        this.securityQuestionID = securityQuestionID;
    }

    public String getSecurityQuestionText() {
        return securityQuestionText;
    }
    public void setSecurityQuestionText(String securityQuestionText) {
        this.securityQuestionText = securityQuestionText;
    }
}
