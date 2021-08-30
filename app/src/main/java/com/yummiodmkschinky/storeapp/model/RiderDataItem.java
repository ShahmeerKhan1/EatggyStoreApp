package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.SerializedName;

public class RiderDataItem{

	@SerializedName("password")
	private String password;

	@SerializedName("address")
	private String address;

	@SerializedName("reject")
	private String reject;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("id")
	private String id;

	@SerializedName("complete")
	private String complete;

	@SerializedName("aid")
	private String aid;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	@SerializedName("accept")
	private String accept;

	@SerializedName("a_status")
	private String aStatus;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setReject(String reject){
		this.reject = reject;
	}

	public String getReject(){
		return reject;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setComplete(String complete){
		this.complete = complete;
	}

	public String getComplete(){
		return complete;
	}

	public void setAid(String aid){
		this.aid = aid;
	}

	public String getAid(){
		return aid;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setAccept(String accept){
		this.accept = accept;
	}

	public String getAccept(){
		return accept;
	}

	public void setAStatus(String aStatus){
		this.aStatus = aStatus;
	}

	public String getAStatus(){
		return aStatus;
	}
}