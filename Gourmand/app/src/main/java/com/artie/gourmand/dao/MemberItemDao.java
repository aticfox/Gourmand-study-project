package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ANFIELD on 28/6/2560.
 */

public class MemberItemDao implements Parcelable {

    @SerializedName("id")               private Integer id;
    @SerializedName("avatar_url")       private String avatarUrl;
    @SerializedName("name")             private String name;

    protected MemberItemDao(Parcel in) {
        id = in.readInt();
        avatarUrl = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(avatarUrl);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MemberItemDao> CREATOR = new Creator<MemberItemDao>() {
        @Override
        public MemberItemDao createFromParcel(Parcel in) {
            return new MemberItemDao(in);
        }

        @Override
        public MemberItemDao[] newArray(int size) {
            return new MemberItemDao[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

}
