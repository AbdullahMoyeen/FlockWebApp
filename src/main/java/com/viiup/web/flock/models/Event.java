package com.viiup.web.flock.models;

import java.security.PrivateKey;
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by HP on 2/23/2015.
 */
public class Event {
    private int eventID;
    private int groupID;
    private String eventName;
    private String eventDescription;
    private Date eventStartDatetime;
    private Date eventEndDatetime;
    private String eventAddressLine1;
    private String eventAddressLine2;
    private String eventCity;
    private String eventStateCode;
    private String eventPostalCode;
    private String eventKeywords;
    private int eventLatitude;
    private int eventLongitude;
    private String privateEventInd;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;

    public int getEventID() { return eventID; }
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getGroupID() {
        return groupID;
    }
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName; }

    public String getEventDescription() {
        return eventDescription;
    }
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;}

    public Date getEventStartDatetime() {
        return eventStartDatetime;
    }
    public void setEventStartDatetime(Date eventStartDatetime) {
        this.eventStartDatetime = eventStartDatetime;
    }

    public Date getEventEndDatetime() {
        return eventEndDatetime;
    }
    public void seteventEndDatetime(Date eventEndDatetime) {
        this.eventEndDatetime = eventEndDatetime;
    }

    public String getEventAddressLine1() {
        return eventAddressLine1;
    }
    public void setEventAddressLine1(String eventAddressLine1) {
        this.eventAddressLine1 = eventAddressLine1;
    }
    public String getEventAddressLine2() {
        return eventAddressLine2;
    }
    public void setEventAddressLine2(String eventAddressLine2) {
        this.eventAddressLine2 = eventAddressLine2;
    }

    public String getEventCity() {
        return eventCity;
    }
    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    public String getEventStateCode() {
        return eventStateCode;
    }
    public void setEventStateCode(String eventStateCode) {
        this.eventStateCode = eventStateCode;
    }

    public String getEventPostalCode() {
        return eventPostalCode;
    }
    public void setEventPostalCode(String eventPostalCode) {
        this.eventPostalCode = eventPostalCode;
    }

    public String getEventKeyword() { return eventKeywords;    }
    public void setEventKeywords(String eventKeywords) {
        this.eventKeywords = eventKeywords;
    }

    public int getEventLatitude() { return eventLatitude; }
    public void setEventLatitude(int eventLatitude) { this.eventLatitude = eventLatitude ; }

    public int getEventLongitude() { return eventLongitude; }
    public void setEventLongitude(int eventLongitude) { this.eventLongitude = eventLongitude;}

    public String getPrivateEventInd() { return privateEventInd; }
    public void setPrivateEventInd(String privateEventInd) {
        this.privateEventInd = privateEventInd;
    }

    public String getCreateUser() { return createUser; }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() { return updateUser; }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() { return updateDate; }
     public void setUpdateDate(Date updateDate) {this.updateDate = updateDate;  }
    }



