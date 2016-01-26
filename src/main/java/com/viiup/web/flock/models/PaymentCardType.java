package com.viiup.web.flock.models;

/**
 * Created by amoyeen on 2/28/15.
 */
public class PaymentCardType {

    private int paymentCardTypeID;
    private String paymentCardTypeName;
    private String paymentCardTypeDescription;

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

    public String getPaymentCardTypeDescription() {
        return paymentCardTypeDescription;
    }
    public void setPaymentCardTypeDescription(String paymentCardTypeDescription) {
        this.paymentCardTypeDescription = paymentCardTypeDescription;
    }
}
