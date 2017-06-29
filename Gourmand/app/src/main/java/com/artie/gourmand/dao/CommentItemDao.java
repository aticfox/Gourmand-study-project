package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ANFIELD on 28/6/2560.
 */

public class CommentItemDao implements Parcelable {

    @SerializedName("member")           private MemberItemDao member;
    @SerializedName("text")             private String text;
    @SerializedName("create_at")        private Date createTime;

    protected CommentItemDao(Parcel in) {
        member = in.readParcelable(MemberItemDao.class.getClassLoader());
        text = in.readString();
        createTime = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(member, flags);
        dest.writeString(text);
        dest.writeLong(createTime.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommentItemDao> CREATOR = new Creator<CommentItemDao>() {
        @Override
        public CommentItemDao createFromParcel(Parcel in) {
            return new CommentItemDao(in);
        }

        @Override
        public CommentItemDao[] newArray(int size) {
            return new CommentItemDao[size];
        }
    };

    public MemberItemDao getMember() {
        return member;
    }

    public String getText() {
        return text;
    }

    public Date getCreateTime() {
        return createTime;
    }

}
