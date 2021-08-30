package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

public class OrderProductDataItem{

	@SerializedName("Product_quantity")
	private String productQuantity;

	@SerializedName("Product_image")
	private String productImage;

	@SerializedName("Product_variation")
	private String productVariation;

	@SerializedName("Product_price")
	private String productPrice;

	@SerializedName("Product_name")
	private String productName;

	@SerializedName("Product_discount")
	private String productDiscount;

	@SerializedName("Product_total")
	private double productTotal;

	public void setProductQuantity(String productQuantity){
		this.productQuantity = productQuantity;
	}

	public String getProductQuantity(){
		return productQuantity;
	}

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setProductVariation(String productVariation){
		this.productVariation = productVariation;
	}

	public String getProductVariation(){
		return productVariation;
	}

	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductDiscount(String productDiscount){
		this.productDiscount = productDiscount;
	}

	public String getProductDiscount(){
		return productDiscount;
	}

	public void setProductTotal(double productTotal){
		this.productTotal = productTotal;
	}

	public double getProductTotal(){
		return productTotal;
	}
}