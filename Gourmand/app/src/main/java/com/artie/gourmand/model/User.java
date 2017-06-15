package com.artie.gourmand.model;

/**
 * Created by ANFIELD on 16/6/2560.
 */

public class User {

    private int mUserImageID;
    private String mUserName;

    public User(int userImageID, String userName) {
        this.mUserImageID = userImageID;
        this.mUserName = userName;
    }

    public int getmUserImageID() {
        return mUserImageID;
    }

    public String getmUserName() {
        return mUserName;
    }
    
}
