package com.viiup.web.flock.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by HP on 2/23/2015.
 */
public class Order {
    private int orderID;
    private int customerID;
    private String orderStatusCode;
    private Date orderCreated;
    private Date orderPlaced;
    private Float orderSubTotal;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingCity;
    private String shippingStateCode;
    private String shippingPostalCode;
    private int stateTaxRateID;
    private Float taxTotal;
    private int shipperID;
    private String shippingSpeedCode;
    private Float shippingTotal;
    private int paymentCardID;
    private Date orderShipped;
    private String trackingNumber;
    private int mergedToOrderID;
    private int orderItemCount;
    private String shippingToName;
    private boolean saveShippingAddress;
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
    private String paymentCardDisplayInd;
    private boolean savePaymentCard;
    private int orderLineCount;
    private Float orderTotal;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(String orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public Date getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    public Date getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(Date orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public Date getOrderShipped() {
        return orderShipped;
    }

    public void setOrderShipped(Date orderShipped) {
        this.orderShipped = orderShipped;
    }

    public Float getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(Float taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Float getOrderSubTotal() {
        return orderSubTotal;
    }

    public void setOrderSubTotal(Float orderSubTotal) {
        this.orderSubTotal = orderSubTotal;
    }

    public Float getShippingTotal() {
        return shippingTotal;
    }

    public void setShippingTotal(Float shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public String getShippingSpeedCode() {
        return shippingSpeedCode;
    }

    public void setShippingSpeedCode(String shippingSpeedCode) {
        this.shippingSpeedCode = shippingSpeedCode;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public int getPaymentCardID() {
        return paymentCardID;
    }

    public void setPaymentCardID(int paymentCardID) {
        this.paymentCardID = paymentCardID;
    }

    public int getStateTaxRateID() {
        return stateTaxRateID;
    }

    public void setStateTaxRateID(int stateTaxRateID) {
        this.stateTaxRateID = stateTaxRateID;
    }

    public String getShippingAddressLine1() {
        return shippingAddressLine1;
    }

    public void setShippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
    }

    public String getShippingAddressLine2() {
        return shippingAddressLine2;
    }

    public void setShippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingStateCode() {
        return shippingStateCode;
    }

    public void setShippingStateCode(String shippingStateCode) {
        this.shippingStateCode = shippingStateCode;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public int getMergedToOrderID() {
        return mergedToOrderID;
    }

    public void setMergedToOrderID(int mergedToOrderID) {
        this.mergedToOrderID = mergedToOrderID;
    }

    public int getOrderItemCount() {
        return orderItemCount;
    }

    public void setOrderItemCount(int orderItemCount) {
        this.orderItemCount = orderItemCount;
    }

    public String getShippingToName() {
        return shippingToName;
    }

    public void setShippingToName(String shippingToName) {
        this.shippingToName = shippingToName;
    }

    public boolean getSaveShippingAddress() {
        return saveShippingAddress;
    }

    public void setSaveShippingAddress(boolean saveShippingAddress) {
        this.saveShippingAddress = saveShippingAddress;
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

    public String getPaymentCardDisplayInd() {
        return paymentCardDisplayInd;
    }

    public void setPaymentCardDisplayInd(String paymentCardDisplayInd) {
        this.paymentCardDisplayInd = paymentCardDisplayInd;
    }

    public boolean getSavePaymentCard() {
        return savePaymentCard;
    }

    public void setSavePaymentCard(boolean savePaymentCard) {
        this.savePaymentCard = savePaymentCard;
    }

    public int getOrderLineCount() {
        return orderLineCount;
    }

    public void setOrderLineCount(int orderLineCount) {
        this.orderLineCount = orderLineCount;
    }

    public Float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Float orderTotal) {
        this.orderTotal = orderTotal;
    }
}
