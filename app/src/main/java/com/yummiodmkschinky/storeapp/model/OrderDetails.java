package com.yummiodmkschinky.storeapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderDetails{

	@SerializedName("ResponseCode")
	private String responseCode;

	@SerializedName("ResponseMsg")
	private String responseMsg;

	@SerializedName("OrderProductList")
	private List<OrderProductListItem> orderProductList;

	@SerializedName("Result")
	private String result;

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

	public void setOrderProductList(List<OrderProductListItem> orderProductList){
		this.orderProductList = orderProductList;
	}

	public List<OrderProductListItem> getOrderProductList(){
		return orderProductList;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}
}