package com.yummiodmkschinky.storeapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductInfoItem implements Parcelable {

	@SerializedName("Product_Out_Stock")
	private String productOutStock;

	@SerializedName("product_type")
	private String productType;

	@SerializedName("product_discount")
	private String productDiscount;

	@SerializedName("attribute_id")
	private String attributeId;

	@SerializedName("product_price")
	private String productPrice;

	protected ProductInfoItem(Parcel in) {
		productOutStock = in.readString();
		productType = in.readString();
		productDiscount = in.readString();
		attributeId = in.readString();
		productPrice = in.readString();
	}

	public static final Creator<ProductInfoItem> CREATOR = new Creator<ProductInfoItem>() {
		@Override
		public ProductInfoItem createFromParcel(Parcel in) {
			return new ProductInfoItem(in);
		}

		@Override
		public ProductInfoItem[] newArray(int size) {
			return new ProductInfoItem[size];
		}
	};

	public void setProductOutStock(String productOutStock){
		this.productOutStock = productOutStock;
	}

	public String getProductOutStock(){
		return productOutStock;
	}

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return productType;
	}

	public void setProductDiscount(String productDiscount){
		this.productDiscount = productDiscount;
	}

	public String getProductDiscount(){
		return productDiscount;
	}

	public void setAttributeId(String attributeId){
		this.attributeId = attributeId;
	}

	public String getAttributeId(){
		return attributeId;
	}

	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}

	public String getProductPrice(){
		return productPrice;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(productOutStock);
		parcel.writeString(productType);
		parcel.writeString(productDiscount);
		parcel.writeString(attributeId);
		parcel.writeString(productPrice);
	}
}