package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

/**
 * Created by ANFIELD on 27/6/2560.
 */

public class PostItemDao implements Parcelable {

    @SerializedName("id")               private Integer id;
    @SerializedName("member")           private MemberItemDao member;
    @SerializedName("image_url")        private String imageURL;
    @SerializedName("caption")          private String caption;
    @SerializedName("liked_count")      private Integer likeCount;
    @SerializedName("is_liked")         private boolean isLike;
    @SerializedName("created_at")       private Long createTime;
    @SerializedName("location_name")    private String locationName;
    @SerializedName("location_lat")     private double latitude;
    @SerializedName("location_long")    private double longitude;

    protected PostItemDao(Parcel in) {
        id = in.readInt();
        member = in.readParcelable(MemberItemDao.class.getClassLoader());
        imageURL = in.readString();
        caption = in.readString();
        likeCount = in.readInt();
        isLike = (in.readInt() == 1)? true : false;
        createTime = in.readLong();
        locationName = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(member, flags);
        dest.writeString(imageURL);
        dest.writeString(caption);
        dest.writeInt(likeCount);
        dest.writeInt(isLike? 1 : 0);
        dest.writeLong(createTime);
        dest.writeString(locationName);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PostItemDao> CREATOR = new Creator<PostItemDao>() {
        @Override
        public PostItemDao createFromParcel(Parcel in) {
            return new PostItemDao(in);
        }

        @Override
        public PostItemDao[] newArray(int size) {
            return new PostItemDao[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public MemberItemDao getMember() {
        return member;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCaption() {
        return caption;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public String getCreateTimeText() {
        return new DateTime(createTime*1000).toString("HH:mma dd/MM/yyyy");
    }

    public String getLocationName() {
        return locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isLike() {
        return isLike;
    }

}
