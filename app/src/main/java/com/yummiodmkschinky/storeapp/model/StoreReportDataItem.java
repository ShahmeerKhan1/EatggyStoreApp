package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

public class StoreReportDataItem{

	@SerializedName("imgurl")
	private String imgurl;

	@SerializedName("report_data")
	private String reportData;

	@SerializedName("title")
	private String title;

	public String getImgurl(){
		return imgurl;
	}

	public String getReportData(){
		return reportData;
	}

	public String getTitle(){
		return title;
	}
}