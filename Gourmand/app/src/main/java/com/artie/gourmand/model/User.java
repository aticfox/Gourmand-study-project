package com.artie.gourmand.model;

/**
 * Created by ANFIELD on 16/6/2560.
 */

public class User {

    private int mUserImageID;
    private String mUserName;
    private Boolean mFollowing;

    public User(int userImageID, String userName, Boolean following) {
        mUserImageID = userImageID;
        mUserName = userName;
        mFollowing = following;
    }

    public int getUserImageID() {
        return mUserImageID;
    }

    public String getUserName() {
        return mUserName;
    }

    public Boolean isFollowing() {
        return mFollowing;
    }

}
