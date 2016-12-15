package com.boxme.githubsample.githhubsample.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vth02 on 15-Dec-16.
 */
public class Repo implements Parcelable {

    private int id;

    private String name;

    private String full_name;

    private String description;

    private int stargazers_count;

    private int watchers_count;

    private Owner owner;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.full_name);
        dest.writeString(this.description);
        dest.writeInt(this.stargazers_count);
        dest.writeInt(this.watchers_count);
        dest.writeParcelable(this.owner, flags);
    }

    public Repo() {
    }

    protected Repo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.full_name = in.readString();
        this.description = in.readString();
        this.stargazers_count = in.readInt();
        this.watchers_count = in.readInt();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
