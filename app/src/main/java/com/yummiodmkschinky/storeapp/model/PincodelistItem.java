package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

public class PincodelistItem{

	@SerializedName("pincode")
	private String pincode;

	@SerializedName("id")
	private String id;

	public String getPincode(){
		return pincode;
	}

	public String getId(){
		return id;
	}
}