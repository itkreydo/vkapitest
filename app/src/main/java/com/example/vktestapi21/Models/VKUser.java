package com.example.vktestapi21.Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class VKUser implements Parcelable {
    int id = 0;
    String firstName = "";
    String lastName = "";
    String photo = "";
    Boolean deactivated = false;

    public VKUser(int id, String firstName, String lastName, String photo, Boolean deactivated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.deactivated = deactivated;
    }

    public VKUser(Parcel parcel) {
        this.id = parcel.readInt();
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.photo = parcel.readString();
        this.deactivated = parcel.readByte() != (byte)0;
    }

    public static final Creator<VKUser> CREATOR = new Creator<VKUser>() {
        @Override
        public VKUser createFromParcel(Parcel in) {
            return new VKUser(in);
        }

        @Override
        public VKUser[] newArray(int size) {
            return new VKUser[size];
        }

        public VKUser parse(JSONObject json){
            VKUser u = new VKUser(json.optInt("id", 0),
                    json.optString("first_name", ""),
                    json.optString("last_name", ""),
                    json.optString("photo_200", ""),
                    json.optBoolean("deactivated", false)
            );
            return u;
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(photo);
        parcel.writeByte((byte)((deactivated) ? 1 : 0));
    }

    public static VKUser parse(JSONObject json){
        VKUser u = new VKUser(json.optInt("id", 0),
                json.optString("first_name", ""),
                json.optString("last_name", ""),
                json.optString("photo_200", ""),
                json.optBoolean("deactivated", false)
        );
        return u;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }
}
