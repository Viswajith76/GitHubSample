package com.boxme.githubsample.githhubsample.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vth02 on 15-Dec-16.
 */
public class Owner implements Parcelable {

    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatar_url);
    }

    public Owner() {
    }

    protected Owner(Parcel in) {
        this.avatar_url = in.readString();
    }

    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel source) {
            return new Owner(source);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
