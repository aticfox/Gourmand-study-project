package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 27/6/2560.
 */

public class PostItemCollectionDao implements Parcelable {

    @SerializedName("posts") private List<PostItemDao> posts;

    public PostItemCollectionDao() {
        posts = new ArrayList<>();
    }

    protected PostItemCollectionDao(Parcel in) {
        in.readTypedList(posts, PostItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(posts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PostItemCollectionDao> CREATOR = new Creator<PostItemCollectionDao>() {
        @Override
        public PostItemCollectionDao createFromParcel(Parcel in) {
            return new PostItemCollectionDao(in);
        }

        @Override
        public PostItemCollectionDao[] newArray(int size) {
            return new PostItemCollectionDao[size];
        }
    };

    public List<PostItemDao> getPosts() {
        return posts;
    }

}
