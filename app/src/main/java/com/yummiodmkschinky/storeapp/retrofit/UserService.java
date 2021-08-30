package com.yummiodmkschinky.storeapp.retrofit;


import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {


    @POST(APIClient.APPEND_URL + "store_login.php")
    Call<JsonObject> getLogin(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "store_appstatus.php")
    Call<JsonObject> getStatus(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "total_order_report.php")
    Call<JsonObject> getDesbord(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "olist.php")
    Call<JsonObject> getOlist(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "complete_order.php")
    Call<JsonObject> getComplete(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "order_status_wise.php")
    Call<JsonObject> getPending(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "noti.php")
    Call<JsonObject> getNoti(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "ostatus.php")
    Call<JsonObject> getOstatus(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "area.php")
    Call<JsonObject> getArea(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "profile.php")
    Call<JsonObject> updateProfile(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "order_product_list.php")
    Call<JsonObject> getOrderDetail(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "total_product_list.php")
    Call<JsonObject> getTotalProduct(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "make_decision.php")
    Call<JsonObject> getMackDecision(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "s_rider_list.php")
    Call<JsonObject> getRiderlist(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "ass_rider.php")
    Call<JsonObject> getAssrider(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "change_stock_status.php")
    Call<JsonObject> changestock(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "get_required_list.php")
    Call<JsonObject> getRequiredlist(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "product_status.php")
    Call<JsonObject> ProductStatus(@Body JsonObject object);

    @POST(APIClient.APPEND_URL + "add_product.php")
    @Multipart
    Call<JsonObject> addProduct(@Part("sid") RequestBody sid,
                                @Part("cid") RequestBody cid,
                                @Part("pid") RequestBody pid,
                                @Part("status") RequestBody status,
                                @Part("productData") RequestBody productData,
                                @Part("title") RequestBody title,
                                @Part("description") RequestBody description,
                                @Part("size") RequestBody size,
                                @Part List<MultipartBody.Part> parts);

}
