package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ANFIELD on 27/6/2560.
 */

public class ProfilePostItemDao implements Parcelable {

    @SerializedName("id")               private Integer id;
    @SerializedName("image_url")        private String imageURL;

    protected ProfilePostItemDao(Parcel in) {
        id = in.readInt();
        imageURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imageURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProfilePostItemDao> CREATOR = new Creator<ProfilePostItemDao>() {
        @Override
        public ProfilePostItemDao createFromParcel(Parcel in) {
            return new ProfilePostItemDao(in);
        }

        @Override
        public ProfilePostItemDao[] newArray(int size) {
            return new ProfilePostItemDao[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getImageURL() {
        return imageURL;
    }

}
