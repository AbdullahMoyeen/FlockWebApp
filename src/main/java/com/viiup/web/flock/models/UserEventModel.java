package com.viiup.web.flock.models;

/**
 * Created by AbdullahMoyeen on 4/14/16.
 */
public class UserEventModel {

    private int userId;
    private boolean isAttending;
    public EventModel event;

     public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public void setIsAttending(boolean isAttending) {
        this.isAttending = isAttending;
    }
}
