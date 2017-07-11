package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANFIELD on 7/7/2560.
 */

public class ProfileItemDao implements Parcelable {

    @SerializedName("id")                   private Integer id;
    @SerializedName("avatar_url")           private String avatarURL;
    @SerializedName("name")                 private String name;
    @SerializedName("follower_count")       private Integer followerCount;
    @SerializedName("following_count")      private Integer followingCount;
    @SerializedName("posts")                private List<PostItemDao> posts;

    protected ProfileItemDao(Parcel in) {
        id = in.readInt();
        avatarURL = in.readString();
        name = in.readString();
        followerCount = in.readInt();
        followingCount = in.readInt();
        in.readTypedList(posts, PostItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(avatarURL);
        dest.writeString(name);
        dest.writeInt(followerCount);
        dest.writeInt(followingCount);
        dest.writeTypedList(posts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProfileItemDao> CREATOR = new Creator<ProfileItemDao>() {
        @Override
        public ProfileItemDao createFromParcel(Parcel in) {
            return new ProfileItemDao(in);
        }

        @Override
        public ProfileItemDao[] newArray(int size) {
            return new ProfileItemDao[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getName() {
        return name;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public List<PostItemDao> getPosts() {
        return posts;
    }
    
}

