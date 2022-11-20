package com.example.sofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Exercise implements Parcelable {

    private String name;

    public Exercise(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                '}';
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    public void writeToParcel(Parcel out, String name) {
        out.writeString(name);
    }

    public static final Parcelable.Creator<Exercise> CREATOR
            = new Parcelable.Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    private Exercise(Parcel in) {
        name = in.readString();
    }
}
