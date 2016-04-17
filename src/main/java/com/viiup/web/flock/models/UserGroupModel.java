package com.viiup.web.flock.models;

/**
 * Created by AbdullahMoyeen on 4/14/16.
 */
public class UserGroupModel {

    private int userId;
    private boolean isMember;
    public GroupModel group;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

<<<<<<< HEAD
    public boolean isMember() {  return isMember ; }

    public void setIsMember(boolean isMember) {  this.isMember = isMember; }
=======
    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }
>>>>>>> origin/master
}
