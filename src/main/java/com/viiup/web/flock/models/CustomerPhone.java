package com.viiup.web.flock.models;

/**
 * Created by amoyeen on 2/18/2015.
 */
public class CustomerPhone {
    private int phoneID;
    private int customerID;
    private String phoneTypeCode;
    private String phoneTypeName;
    private String phoneNumber;

    public int getPhoneID() {
        return phoneID;
    }
    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPhoneTypeCode() {
        return phoneTypeCode;
    }
    public void setPhoneTypeCode(String phoneType) {
        this.phoneTypeCode = phoneType;
    }

    public String getPhoneTypeName() {
        return phoneTypeName;
    }
    public void setPhoneTypeName(String phoneTypeName) {
        this.phoneTypeName = phoneTypeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
