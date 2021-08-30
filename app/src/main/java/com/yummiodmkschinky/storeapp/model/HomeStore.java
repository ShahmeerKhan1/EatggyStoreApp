package com.yummiodmkschinky.storeapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HomeStore{

	@SerializedName("ResponseCode")
	private String responseCode;

	@SerializedName("store_report_data")
	private List<StoreReportDataItem> storeReportData;

	@SerializedName("ResponseMsg")
	private String responseMsg;

	@SerializedName("Result")
	private String result;

	public String getResponseCode(){
		return responseCode;
	}

	public List<StoreReportDataItem> getStoreReportData(){
		return storeReportData;
	}

	public String getResponseMsg(){
		return responseMsg;
	}

	public String getResult(){
		return result;
	}
}