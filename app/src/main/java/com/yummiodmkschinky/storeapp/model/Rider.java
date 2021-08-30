package com.yummiodmkschinky.storeapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Rider{

	@SerializedName("RiderData")
	private List<RiderDataItem> riderData;

	@SerializedName("ResponseCode")
	private String responseCode;

	@SerializedName("ResponseMsg")
	private String responseMsg;

	@SerializedName("Result")
	private String result;

	public void setRiderData(List<RiderDataItem> riderData){
		this.riderData = riderData;
	}

	public List<RiderDataItem> getRiderData(){
		return riderData;
	}

	public void setResponseCode(String responseCode){
		this.responseCode = responseCode;
	}

	public String getResponseCode(){
		return responseCode;
	}

	public void setResponseMsg(String responseMsg){
		this.responseMsg = responseMsg;
	}

	public String getResponseMsg(){
		return responseMsg;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}
}