package com.artie.gourmand.model;

/**
 * Created by ANFIELD on 15/6/2560.
 */

public class Post {

    private int mUserImageID;
    private int mPostImageID;
    private String mUserName;

    public Post(int userImageID, int postImageID, String userName) {
        mUserImageID = userImageID;
        mPostImageID = postImageID;
        mUserName = userName;
    }

    public int getmUserImageID() {
        return mUserImageID;
    }

    public int getmPostImageID() {
        return mPostImageID;
    }

    public String getmUserName() {
        return mUserName;
    }

}
