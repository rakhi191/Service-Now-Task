
package com.servicenowtask.app.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("owner")
    @Expose
    public String owner;
    @SerializedName("secret")
    @Expose
    public String secret;
    @SerializedName("server")
    @Expose
    public String server;
    @SerializedName("farm")
    @Expose
    public Integer farm;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("ispublic")
    @Expose
    public Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    public Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    public Integer isfamily;

    protected Photo(Parcel in) {
        id = in.readString();
        owner = in.readString();
        secret = in.readString();
        server = in.readString();
        if (in.readByte() == 0) {
            farm = null;
        } else {
            farm = in.readInt();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            ispublic = null;
        } else {
            ispublic = in.readInt();
        }
        if (in.readByte() == 0) {
            isfriend = null;
        } else {
            isfriend = in.readInt();
        }
        if (in.readByte() == 0) {
            isfamily = null;
        } else {
            isfamily = in.readInt();
        }
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(owner);
        parcel.writeString(secret);
        parcel.writeString(server);
        if (farm == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(farm);
        }
        parcel.writeString(title);
        if (ispublic == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ispublic);
        }
        if (isfriend == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isfriend);
        }
        if (isfamily == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isfamily);
        }
    }
}
