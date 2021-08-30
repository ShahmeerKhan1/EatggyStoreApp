package com.yummiodmkschinky.storeapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataCP {

	@SerializedName("Catlist")
	private List<CatlistItem> catlist;

	@SerializedName("Pincodelist")
	private List<PincodelistItem> pincodelist;

	public List<CatlistItem> getCatlist(){
		return catlist;
	}

	public List<PincodelistItem> getPincodelist(){
		return pincodelist;
	}
}