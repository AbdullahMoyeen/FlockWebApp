package com.viiup.web.flock.models;

import java.util.Date;

/**
 * Created by amoyeen on 2/18/2015.
 */
public class CustomerPaymentCard {
    private int paymentCardID;
    private int customerID;
    private int paymentCardTypeID;
    private String paymentCardTypeName;
    private String paymentCardNumber;
    private String paymentCardCVV;
    private String paymentCardExpiry;
    private String paymentCardHolderName;
    private String paymentCardAddressLine1;
    private String paymentCardAddressLine2;
    private String paymentCardCity;
    private String paymentCardStateCode;
    private String paymentCardPostalCode;
    private String displayInd;
    private String paymentCardFullAddress;

    public int getPaymentCardID() {
        return paymentCardID;
    }
    public void setPaymentCardID(int paymentCardID) {
        this.paymentCardID = paymentCardID;
    }

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getPaymentCardTypeID() {
        return paymentCardTypeID;
    }
    public void setPaymentCardTypeID(int paymentCardTypeID) {
        this.paymentCardTypeID = paymentCardTypeID;
    }

    public String getPaymentCardTypeName() {
        return paymentCardTypeName;
    }
    public void setPaymentCardTypeName(String paymentCardTypeName) {
        this.paymentCardTypeName = paymentCardTypeName;
    }

    public String getPaymentCardNumber() {
        return paymentCardNumber;
    }
    public void setPaymentCardNumber(String paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public String getPaymentCardCVV() {
        return paymentCardCVV;
    }
    public void setPaymentCardCVV(String paymentCardCVV) {
        this.paymentCardCVV = paymentCardCVV;
    }

    public String getPaymentCardExpiry() {
        return paymentCardExpiry;
    }
    public void setPaymentCardExpiry(String paymentCardExpiry) {
        this.paymentCardExpiry = paymentCardExpiry;
    }

    public String getPaymentCardHolderName() {
        return paymentCardHolderName;
    }
    public void setPaymentCardHolderName(String paymentCardHolderName) {
        this.paymentCardHolderName = paymentCardHolderName;
    }

    public String getPaymentCardAddressLine1() {
        return paymentCardAddressLine1;
    }
    public void setPaymentCardAddressLine1(String paymentCardAddressLine1) {
        this.paymentCardAddressLine1 = paymentCardAddressLine1;
    }

    public String getPaymentCardAddressLine2() {
        return paymentCardAddressLine2;
    }
    public void setPaymentCardAddressLine2(String paymentCardAddressLine2) {
        this.paymentCardAddressLine2 = paymentCardAddressLine2;
    }

    public String getPaymentCardCity() {
        return paymentCardCity;
    }
    public void setPaymentCardCity(String paymentCardCity) {
        this.paymentCardCity = paymentCardCity;
    }

    public String getPaymentCardStateCode() {
        return paymentCardStateCode;
    }
    public void setPaymentCardStateCode(String paymentCardStateCode) {
        this.paymentCardStateCode = paymentCardStateCode;
    }

    public String getPaymentCardPostalCode() {
        return paymentCardPostalCode;
    }
    public void setPaymentCardPostalCode(String paymentCardPostalCode) {
        this.paymentCardPostalCode = paymentCardPostalCode;
    }

    public String getDisplayInd() {
        return displayInd;
    }
    public void setDisplayInd(String displayInd) {
        this.displayInd = displayInd;
    }

    public String getPaymentCardFullAddress() {
        return paymentCardFullAddress;
    }
    public void setPaymentCardFullAddress(String paymentCardFullAddress) {
        this.paymentCardFullAddress = paymentCardFullAddress;
    }
}
