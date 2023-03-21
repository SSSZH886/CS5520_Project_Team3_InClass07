package com.example.inclass_zhuohan_926923.InClass_03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Profile implements Parcelable {
    private String name, email, mood, device;
    private int avatarDrawable;

    public Profile(){

    }

    public Profile(String name, String email, String device, String mood, int avatarDrawable) {
        this.name = name;
        this.email = email;
        this.mood = mood;
        this.device = device;
        this.avatarDrawable = avatarDrawable;
    }

    protected Profile(Parcel in) {
        name = in.readString();
        email = in.readString();
        device = in.readString();
        mood = in.readString();
        avatarDrawable = in.readInt();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(mood);
        parcel.writeString(device);
        parcel.writeInt(avatarDrawable);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getAvatarDrawable() {
        return avatarDrawable;
    }

    public void setAvatarDrawable(int avatarDrawable) {
        this.avatarDrawable = avatarDrawable;
    }


}