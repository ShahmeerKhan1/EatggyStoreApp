package com.yummiodmkschinky.storeapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderProductListItem{

	@SerializedName("Delivery_charge")
	private String deliveryCharge;

	@SerializedName("p_method_name")
	private String pMethodName;

	@SerializedName("customer_address")
	private String customerAddress;

	@SerializedName("Order_Status")
	private String orderStatus;

	@SerializedName("Order_Total")
	private String orderTotal;

	@SerializedName("Coupon_Amount")
	private String couponAmount;

	@SerializedName("comment_reject")
	private String commentReject;

	@SerializedName("Order_flow_id")
	private String orderFlowId;

	@SerializedName("Order_Product_Data")
	private List<OrderProductDataItem> orderProductData;

	@SerializedName("order_date")
	private String orderDate;

	@SerializedName("Additional_Note")
	private String additionalNote;

	@SerializedName("Order_Transaction_id")
	private String orderTransactionId;

	@SerializedName("Order_SubTotal")
	private String orderSubTotal;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("customer_mobile")
	private String customerMobile;

	@SerializedName("customer_name")
	private String customerName;

	@SerializedName("customer_email")
	private String customerEmail;

	public void setDeliveryCharge(String deliveryCharge){
		this.deliveryCharge = deliveryCharge;
	}

	public String getDeliveryCharge(){
		return deliveryCharge;
	}

	public void setPMethodName(String pMethodName){
		this.pMethodName = pMethodName;
	}

	public String getPMethodName(){
		return pMethodName;
	}

	public void setCustomerAddress(String customerAddress){
		this.customerAddress = customerAddress;
	}

	public String getCustomerAddress(){
		return customerAddress;
	}

	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public void setOrderTotal(String orderTotal){
		this.orderTotal = orderTotal;
	}

	public String getOrderTotal(){
		return orderTotal;
	}

	public void setCouponAmount(String couponAmount){
		this.couponAmount = couponAmount;
	}

	public String getCouponAmount(){
		return couponAmount;
	}

	public void setCommentReject(String commentReject){
		this.commentReject = commentReject;
	}

	public String getCommentReject(){
		return commentReject;
	}

	public void setOrderFlowId(String orderFlowId){
		this.orderFlowId = orderFlowId;
	}

	public String getOrderFlowId(){
		return orderFlowId;
	}

	public void setOrderProductData(List<OrderProductDataItem> orderProductData){
		this.orderProductData = orderProductData;
	}

	public List<OrderProductDataItem> getOrderProductData(){
		return orderProductData;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public void setAdditionalNote(String additionalNote){
		this.additionalNote = additionalNote;
	}

	public String getAdditionalNote(){
		return additionalNote;
	}

	public void setOrderTransactionId(String orderTransactionId){
		this.orderTransactionId = orderTransactionId;
	}

	public String getOrderTransactionId(){
		return orderTransactionId;
	}

	public void setOrderSubTotal(String orderSubTotal){
		this.orderSubTotal = orderSubTotal;
	}

	public String getOrderSubTotal(){
		return orderSubTotal;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}