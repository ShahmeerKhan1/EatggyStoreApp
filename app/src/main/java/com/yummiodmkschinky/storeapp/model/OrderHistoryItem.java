package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderHistoryItem {

    @SerializedName("order_date")
    private String orderDate;

    @SerializedName("total")
    private String total;

    @SerializedName("id")
    private String id;

    @SerializedName("status")
    private String status;

    @SerializedName("cust_add")
    private String custAdd;

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDate() {


        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
            Date date = inputFormat.parse(orderDate);
            return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return orderDate;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getCustAdd() {
        return custAdd;
    }
}