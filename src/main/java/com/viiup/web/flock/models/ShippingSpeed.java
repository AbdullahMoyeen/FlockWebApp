package com.viiup.web.flock.models;

/**
 * Created by HP on 2/26/2015.
 */
public class ShippingSpeed {
    private String shippingSpeedCode;
    private String shippingSpeedName;
    private String shippingSpeedDescription;

    public String getShippingSpeedCode(){ return shippingSpeedCode; }
    public void setShippingSpeedCode(String shippingSpeedCode) { this.shippingSpeedCode= shippingSpeedCode; }

    public String getShippingSpeedName(){ return shippingSpeedName; }
    public void setShippingSpeedName(String shippingSpeedName) { this.shippingSpeedName= shippingSpeedName; }

    public String getShippingSpeedDescription(){ return shippingSpeedDescription; }
    public void setShippingSpeedDescription(String shippingSpeedDescription) { this.shippingSpeedDescription= shippingSpeedDescription; }

}
