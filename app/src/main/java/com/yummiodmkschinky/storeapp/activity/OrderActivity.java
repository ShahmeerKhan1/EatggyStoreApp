package com.yummiodmkschinky.storeapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.yummiodmkschinky.storeapp.R;
import com.yummiodmkschinky.storeapp.model.OrderDetail;
import com.yummiodmkschinky.storeapp.model.OrderItemsItem;
import com.yummiodmkschinky.storeapp.model.RestResponse;
import com.yummiodmkschinky.storeapp.model.RiderDataItem;
import com.yummiodmkschinky.storeapp.model.Store;
import com.yummiodmkschinky.storeapp.retrofit.APIClient;
import com.yummiodmkschinky.storeapp.retrofit.GetResult;
import com.yummiodmkschinky.storeapp.utils.CustPrograssbar;
import com.yummiodmkschinky.storeapp.utils.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.yummiodmkschinky.storeapp.retrofit.APIClient.baseUrl;
import static com.yummiodmkschinky.storeapp.utils.SessionManager.curruncy;


public class OrderActivity extends AppCompatActivity implements GetResult.MyListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_rtitle)
    TextView txtRtitle;
    @BindView(R.id.txt_rlocation)
    TextView txtRlocation;
    @BindView(R.id.txt_customer)
    TextView txtCustomer;
    @BindView(R.id.txt_caddress)
    TextView txtCaddress;
    @BindView(R.id.lvl_completdate)
    LinearLayout lvlCompletdate;
    @BindView(R.id.lvl_rider)
    LinearLayout lvlRider;
    @BindView(R.id.txt_deliveryboy)
    TextView txtDeliveryboy;
    @BindView(R.id.txt_completdate)
    TextView txtCompletdate;
    @BindView(R.id.lvl_itmelist)
    LinearLayout lvlItmelist;
    @BindView(R.id.txt_itemtotal)
    TextView txtItemtotal;
    @BindView(R.id.txt_dcharge)
    TextView txtDcharge;
    @BindView(R.id.lvl_discount)
    LinearLayout lvlDiscount;
    @BindView(R.id.txt_discount)
    TextView txtDiscount;
    @BindView(R.id.txt_pmethod)
    TextView txtPmethod;
    @BindView(R.id.txt_topay)
    TextView txtTopay;
    @BindView(R.id.txt_orderid)
    TextView txtOrderid;

    @BindView(R.id.lvl_deliverytips)
    LinearLayout lvlDeliverytips;
    @BindView(R.id.txt_dtips)
    TextView txtDtips;
    @BindView(R.id.lvl_storecharge)
    LinearLayout lvlStorecharge;
    @BindView(R.id.txt_storecharge)
    TextView txtStorecharge;
    @BindView(R.id.lvl_texandcharge)
    LinearLayout lvlTexandcharge;
    @BindView(R.id.txt_taxcharge)
    TextView txtTaxcharge;
    @BindView(R.id.lvl_wallet)
    LinearLayout lvlWallet;

    @BindView(R.id.txt_wallet)
    TextView txtWallet;

    @BindView(R.id.img_rider)
    ImageView imgRider;
    @BindView(R.id.txt_name)
    TextView txtName;


    @BindView(R.id.lvl_makedecision)
    LinearLayout lvlMakedecision;
    @BindView(R.id.txt_makedecision)
    TextView txtMakedecision;

    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    String oid;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        sessionManager = new SessionManager(this);
        custPrograssbar = new CustPrograssbar();
        oid = getIntent().getStringExtra("oid");
        store = sessionManager.getUserDetails("");
        getOrderItem();
    }

    private void getOrderItem() {
        custPrograssbar.prograssCreate(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("order_id", oid);
            jsonObject.put("sid", store.getId());
            JsonParser jsonParser = new JsonParser();
            Call<JsonObject> call = APIClient.getInterface().getOrderDetail((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    OrderDetail orderDetail;

    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                orderDetail = gson.fromJson(result.toString(), OrderDetail.class);
                if (orderDetail.getResult().equalsIgnoreCase("true")) {
                    txtRtitle.setText("" + orderDetail.getOrderInfo().getRestName());
                    txtRlocation.setText("" + orderDetail.getOrderInfo().getRestAddress());
                    txtCustomer.setText("" + orderDetail.getOrderInfo().getAddressType());
                    txtCaddress.setText("" + orderDetail.getOrderInfo().getCustAddress());

                    txtItemtotal.setText(sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getSubtotal());
                    txtDcharge.setText("+" + sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getDeliveryCharge());


                    if (orderDetail.getOrderInfo().getCouAmt() != null && orderDetail.getOrderInfo().getCouAmt().equalsIgnoreCase("0")) {
                        lvlDiscount.setVisibility(View.GONE);
                    } else {
                        txtDiscount.setText("-" + sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getCouAmt());
                    }

                    if (orderDetail.getOrderInfo().getTax() != null && orderDetail.getOrderInfo().getTax().equalsIgnoreCase("0")) {
                        lvlTexandcharge.setVisibility(View.GONE);
                    } else {
                        txtTaxcharge.setText("+" + sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getTax());
                    }

                    if (orderDetail.getOrderInfo().getRestCharge() != null && orderDetail.getOrderInfo().getRestCharge().equalsIgnoreCase("0")) {
                        lvlStorecharge.setVisibility(View.GONE);
                    } else {
                        txtStorecharge.setText("+" + sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getRestCharge());
                    }
                    if (orderDetail.getOrderInfo().getRiderTip() != null && orderDetail.getOrderInfo().getRiderTip().equalsIgnoreCase("0")) {
                        lvlDeliverytips.setVisibility(View.GONE);
                    } else {
                        txtDtips.setText(sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getRiderTip());
                    }

                    if (orderDetail.getOrderInfo().getWallAmt() != null && orderDetail.getOrderInfo().getWallAmt().equalsIgnoreCase("0")) {
                        lvlWallet.setVisibility(View.GONE);
                    } else {
                        txtWallet.setText("-" + sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getWallAmt());
                    }


                    txtTopay.setText(sessionManager.getStringData(curruncy) + orderDetail.getOrderInfo().getOrderTotal());
                    txtPmethod.setText("" + orderDetail.getOrderInfo().getPMethodName());
                    txtOrderid.setText("ORDERID #" + orderDetail.getOrderInfo().getOrderId());
                    if (orderDetail.getOrderInfo().getOStatus().equalsIgnoreCase("Completed")) {
                        lvlCompletdate.setVisibility(View.VISIBLE);
                        txtDeliveryboy.setText("" + orderDetail.getOrderInfo().getRiderName());
                        txtCompletdate.setText("" + orderDetail.getOrderInfo().getOrderCompleteDate());
                    } else if (orderDetail.getOrderInfo().getOStatus().equalsIgnoreCase("Processing") || orderDetail.getOrderInfo().getOStatus().equalsIgnoreCase("On Route")) {
                        lvlRider.setVisibility(View.VISIBLE);
                        txtName.setText("" + orderDetail.getOrderInfo().getRiderName());
                        Glide.with(this).load(baseUrl + orderDetail.getOrderInfo().getRiderImg()).placeholder(R.drawable.slider).into(imgRider);
                    }

                    if (orderDetail.getOrderInfo().getOrderFlowId().equalsIgnoreCase("0")) {
                        lvlMakedecision.setVisibility(View.VISIBLE);
                    }


                    setNotiList(lvlItmelist, orderDetail.getOrderInfo().getOrderItems());
                }
            } else if (callNo.equalsIgnoreCase("2")) {
                Gson gson = new Gson();
                RestResponse response = gson.fromJson(result, RestResponse.class);
                Toast.makeText(this, response.getResponseMsg(), Toast.LENGTH_SHORT).show();
                if (response.getResult().equalsIgnoreCase("true")) {

                    lvlMakedecision.setVisibility(View.GONE);
                    finish();
                } else {
                    finish();
                }
            }
        } catch (Exception e) {
            Log.e("Error", "-->" + e.toString());
        }

    }

    private void setNotiList(LinearLayout lnrView, List<OrderItemsItem> list) {
        lnrView.removeAllViews();


        for (int i = 0; i < list.size(); i++) {
            LayoutInflater inflater = LayoutInflater.from(OrderActivity.this);

            View view = inflater.inflate(R.layout.item_orderitem, null);

            TextView txtTitel = view.findViewById(R.id.txt_title);
            TextView txtPextra = view.findViewById(R.id.txt_pextra);
            TextView txtPrice = view.findViewById(R.id.txt_price);

            switch (list.get(i).getIsVeg()) {
                case "0":
                    txtTitel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_nonveg, 0, 0, 0);
                    break;
                case "1":
                    txtTitel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_veg, 0, 0, 0);
                    break;
                case "2":
                    txtTitel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_veg, 0, 0, 0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + list.get(i).getIsVeg());
            }
            txtTitel.setText("" + list.get(i).getItemName());
            txtPextra.setText("" + list.get(i).getItemAddon());
            txtPrice.setText("" + sessionManager.getStringData(curruncy) + list.get(i).getItemTotal());
            lnrView.addView(view);

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.img_back, R.id.txt_makedecision, R.id.img_call})
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.txt_makedecision:
                bottonOrderMakeDecision();
                break;


            case R.id.img_back:
                finish();
                break;
            case R.id.img_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + orderDetail.getOrderInfo().getRiderMobile()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getMackDecision(String status) {
        custPrograssbar.prograssCreate(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("oid", oid);
            jsonObject.put("status", status);
            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().getMackDecision((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void bottonOrderMakeDecision() {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.order_makedecision, null);
        mBottomSheetDialog.setContentView(sheetView);

        TextView txtAccept = sheetView.findViewById(R.id.txt_accept);
        TextView txtReject = sheetView.findViewById(R.id.txt_reject);
        txtAccept.setOnClickListener(view -> {
            mBottomSheetDialog.cancel();
            getMackDecision("1");
        });
        txtReject.setOnClickListener(view -> {
            mBottomSheetDialog.cancel();
            getMackDecision("2");
        });
        mBottomSheetDialog.show();
    }

    List<RiderDataItem> arrayList = new ArrayList<>();


}