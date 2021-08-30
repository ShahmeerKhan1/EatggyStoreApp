package com.yummiodmkschinky.storeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddonItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private String price;
    private boolean isSelect =false;

    protected AddonItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        price = in.readString();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<AddonItem> CREATOR = new Creator<AddonItem>() {
        @Override
        public AddonItem createFromParcel(Parcel in) {
            return new AddonItem(in);
        }

        @Override
        public AddonItem[] newArray(int size) {
            return new AddonItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(price);
        parcel.writeByte((byte) (isSelect ? 1 : 0));
    }
}