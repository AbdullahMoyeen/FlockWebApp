package com.viiup.web.flock.models;

/**
 * Created by amoyeen on 2/18/2015.
 */
public class PasswordSecurity {
    private int customerID;
    private String emailAddress;
    private int securityQuestionID;
    private String securityQuestionText;
    private String securityQuestionAnswer;

    public int getCustomerID(){
        return customerID;
    }
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public String getEmailAddress(){
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public int getSecurityQuestionID(){
        return securityQuestionID;
    }
    public void setSecurityQuestionID(int securityQuestionID){
        this.securityQuestionID = securityQuestionID;
    }

    public String getSecurityQuestionText(){
        return securityQuestionText;
    }
    public void setSecurityQuestionText(String securityQuestionText){
        this.securityQuestionText = securityQuestionText;
    }

    public String getSecurityQuestionAnswer(){
        return securityQuestionAnswer;
    }
    public void setSecurityQuestionAnswer(String securityQuestionAnswer){
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}
