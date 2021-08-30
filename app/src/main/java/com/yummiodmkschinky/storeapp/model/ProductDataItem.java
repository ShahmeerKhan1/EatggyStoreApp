package com.yummiodmkschinky.storeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductDataItem implements Parcelable {

	@SerializedName("product_image")
	private String productImage;

	@SerializedName("addondata")
	@Expose
	private ArrayList<Addondata> addondata = null;

	@SerializedName("id")
	private String id;

	@SerializedName("short_desc")
	private String shortDesc;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("product_category")
	private String productCategory;

	@SerializedName("product_status")
	private int productStatus;

	@SerializedName("product_price")
	private String productPrice;

	@SerializedName("is_veg")
	private String isVeg;


	protected ProductDataItem(Parcel in) {
		productImage = in.readString();
		addondata = in.createTypedArrayList(Addondata.CREATOR);
		id = in.readString();
		shortDesc = in.readString();
		productName = in.readString();
		productCategory = in.readString();
		productStatus = in.readInt();
		productPrice = in.readString();
		isVeg = in.readString();
	}

	public static final Creator<ProductDataItem> CREATOR = new Creator<ProductDataItem>() {
		@Override
		public ProductDataItem createFromParcel(Parcel in) {
			return new ProductDataItem(in);
		}

		@Override
		public ProductDataItem[] newArray(int size) {
			return new ProductDataItem[size];
		}
	};

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public ArrayList<Addondata> getAddondata() {
		return addondata;
	}

	public void setAddondata(ArrayList<Addondata> addondata) {
		this.addondata = addondata;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(String isVeg) {
		this.isVeg = isVeg;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(productImage);
		parcel.writeTypedList(addondata);
		parcel.writeString(id);
		parcel.writeString(shortDesc);
		parcel.writeString(productName);
		parcel.writeString(productCategory);
		parcel.writeInt(productStatus);
		parcel.writeString(productPrice);
		parcel.writeString(isVeg);
	}
}