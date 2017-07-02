package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 2/7/2560.
 */

public class CommentItemCollectionDao implements Parcelable {

    @SerializedName("comments") private List<CommentItemDao> comments;

    public CommentItemCollectionDao() {
        comments = new ArrayList<>();
    }

    protected CommentItemCollectionDao(Parcel in) {
        comments = in.createTypedArrayList(CommentItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(comments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommentItemCollectionDao> CREATOR = new Creator<CommentItemCollectionDao>() {
        @Override
        public CommentItemCollectionDao createFromParcel(Parcel in) {
            return new CommentItemCollectionDao(in);
        }

        @Override
        public CommentItemCollectionDao[] newArray(int size) {
            return new CommentItemCollectionDao[size];
        }
    };

    public List<CommentItemDao> getComments() {
        return comments;
    }
    
}
