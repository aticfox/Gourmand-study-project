package com.artie.gourmand.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 1/7/2560.
 */

public class MemberItemCollectionDao implements Parcelable {

    @SerializedName("members") private List<MemberItemDao> members;

    public MemberItemCollectionDao() {
        members = new ArrayList<>();
    }

    public MemberItemCollectionDao(List<MemberItemDao> members) {
        this.members = members;
    }

    protected MemberItemCollectionDao(Parcel in) {
        members = in.createTypedArrayList(MemberItemDao.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(members);
    }

    public static final Creator<MemberItemCollectionDao> CREATOR = new Creator<MemberItemCollectionDao>() {
        @Override
        public MemberItemCollectionDao createFromParcel(Parcel in) {
            return new MemberItemCollectionDao(in);
        }

        @Override
        public MemberItemCollectionDao[] newArray(int size) {
            return new MemberItemCollectionDao[size];
        }
    };

    public List<MemberItemDao> getMembers() {
        return members;
    }
    
}
