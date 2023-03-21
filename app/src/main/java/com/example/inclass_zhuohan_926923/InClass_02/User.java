package com.example.inclass_zhuohan_926923.InClass_02;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String name;
    String email;
    String device;
    String mood;
    String avatar_image;


    public User(String name, String email, String device, String mood, String avatar_image) {
        this.name = name;
        this.email = email;
        this.device = device;
        this.mood = mood;
        this.avatar_image = avatar_image;

    }

    public User(){}

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        device = in.readString();
        mood = in.readString();
        avatar_image = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", device='" + device + '\'' +
                ", mood='" + mood + '\'' +
                ", avatar_image='" + avatar_image + '\''  +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(device);
        parcel.writeString(mood);
        parcel.writeString(avatar_image);
    }
}
