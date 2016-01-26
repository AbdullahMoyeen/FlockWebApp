package com.viiup.web.flock.models;

/**
 * Created by amoyeen on 2/27/15.
 */
public class AddressState {

    private String stateCode;
    private String stateName;
    private String shippingAllowedInd;
    private String stateCodeAndName;

    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getShippingAllowedInd() {
        return shippingAllowedInd;
    }
    public void setShippingAllowedInd(String shippingAllowedInd) {
        this.shippingAllowedInd = shippingAllowedInd;
    }

    public String getStateCodeAndName() {
        return stateCodeAndName;
    }
    public void setStateCodeAndName(String stateCodeAndName) {
        this.stateCodeAndName = stateCodeAndName;
    }
}
