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
    @SerializedName("location_lat")     private double latitude;
    @SerializedName("location_long")    private double longitude;

    protected ProfilePostItemDao(Parcel in) {
        id = in.readInt();
        imageURL = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imageURL);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocationName() {
        return "Central Location";
    }

}
