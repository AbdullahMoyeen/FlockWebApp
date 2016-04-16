package com.viiup.web.flock.models;

import java.util.Date;

/**
 * Created by MP on 1/27/2016.
 */
public class EventUserRsvpModel {

    private int eventUserRsvpId;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;
    private int eventId;
    private int userId;
    private String rsvpTypeCode;


    public int getEventUserRsvpId() {
        return eventUserRsvpId;
    }

    public void setEventUserRsvpId(int eventUserRsvpId) {
        this.eventUserRsvpId = eventUserRsvpId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRsvpTypeCode() {
        return rsvpTypeCode;
    }

    public void setRsvpTypeCode(String rsvpTypeCode) {
        this.rsvpTypeCode = rsvpTypeCode;
    }
}
