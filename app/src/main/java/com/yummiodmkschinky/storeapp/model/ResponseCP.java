package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseCP {

	@SerializedName("ResponseCode")
	private String responseCode;

	@SerializedName("ResultData")
	private DataCP resultData;

	@SerializedName("ResponseMsg")
	private String responseMsg;

	@SerializedName("Result")
	private String result;

	public String getResponseCode(){
		return responseCode;
	}

	public DataCP getResultData(){
		return resultData;
	}

	public String getResponseMsg(){
		return responseMsg;
	}

	public String getResult(){
		return result;
	}
}