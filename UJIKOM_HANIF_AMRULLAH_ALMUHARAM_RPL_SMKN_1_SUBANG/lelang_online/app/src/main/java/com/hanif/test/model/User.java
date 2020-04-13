package com.hanif.test.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String idUser;
    public String idLevel;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idUser);
        dest.writeString(this.idLevel);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.idUser = in.readString();
        this.idLevel = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
