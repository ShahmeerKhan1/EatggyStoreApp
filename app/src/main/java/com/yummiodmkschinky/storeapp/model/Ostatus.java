
package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Ostatus {

    @SerializedName("order_data")
    private OrderStatus mOrderData;
    @SerializedName("ResponseCode")
    private String mResponseCode;
    @SerializedName("ResponseMsg")
    private String mResponseMsg;
    @SerializedName("Result")
    private String mResult;

    public OrderStatus getOrderData() {
        return mOrderData;
    }

    public void setOrderData(OrderStatus orderData) {
        mOrderData = orderData;
    }

    public String getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(String responseCode) {
        mResponseCode = responseCode;
    }

    public String getResponseMsg() {
        return mResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        mResponseMsg = responseMsg;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
