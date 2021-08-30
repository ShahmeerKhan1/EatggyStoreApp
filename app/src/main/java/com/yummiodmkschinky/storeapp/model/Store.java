
package com.yummiodmkschinky.storeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Store {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("aid")
    @Expose
    private String aid;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("upimob")
    @Expose
    private String upimob;
    @SerializedName("accnum")
    @Expose
    private String accnum;
    @SerializedName("accname")
    @Expose
    private String accname;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("v_img")
    @Expose
    private String vImg;
    @SerializedName("scat")
    @Expose
    private String scat;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("vstatus")
    @Expose
    private String vstatus;
    @SerializedName("popular")
    @Expose
    private String popular;
    @SerializedName("star")
    @Expose
    private String star;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpimob() {
        return upimob;
    }

    public void setUpimob(String upimob) {
        this.upimob = upimob;
    }

    public String getAccnum() {
        return accnum;
    }

    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVImg() {
        return vImg;
    }

    public void setVImg(String vImg) {
        this.vImg = vImg;
    }

    public String getScat() {
        return scat;
    }

    public void setScat(String scat) {
        this.scat = scat;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }



}
