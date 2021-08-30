package com.yummiodmkschinky.storeapp.fregment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yummiodmkschinky.storeapp.R;
import com.yummiodmkschinky.storeapp.activity.OrderActivity;
import com.yummiodmkschinky.storeapp.model.OrderHistoryItem;
import com.yummiodmkschinky.storeapp.model.Pending;
import com.yummiodmkschinky.storeapp.model.Store;
import com.yummiodmkschinky.storeapp.retrofit.APIClient;
import com.yummiodmkschinky.storeapp.retrofit.GetResult;
import com.yummiodmkschinky.storeapp.utils.CustPrograssbar;
import com.yummiodmkschinky.storeapp.utils.SessionManager;
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

import static com.yummiodmkschinky.storeapp.utils.SessionManager.curruncy;


public class PendingFragment extends Fragment implements GetResult.MyListener{

    @BindView(R.id.recycle_pending)
    RecyclerView recyclePending;



    @BindView(R.id.txt_titel)
    TextView txtTitle;
    @BindView(R.id.txt_neworder)
    TextView txtNeworder;
    @BindView(R.id.txt_ongoing)
    TextView txtOngoing;
    @BindView(R.id.txt_complet)
    TextView txtComplet;
    @BindView(R.id.txtNodata)
    TextView txtNodata;
    public PendingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    Store user;
    List<OrderHistoryItem> pendinglistMain = new ArrayList<>();
    PendingAdepter myOrderAdepter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pendding, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recyclePending.setLayoutManager(recyclerLayoutManager);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());
        user = sessionManager.getUserDetails("");
        txtTitle.setText("Pending Order");
        getPendingOrder("Pending");

        return view;
    }




    @OnClick({R.id.txt_neworder, R.id.txt_ongoing, R.id.txt_complet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_neworder:
                getPendingOrder("Pending");
                txtTitle.setText("New Order");
                txtNeworder.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t1));
                txtComplet.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t2));
                txtOngoing.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t2));

                txtNeworder.setTextColor(getActivity().getResources().getColor(R.color.selectcoler));
                txtComplet.setTextColor(getActivity().getResources().getColor(R.color.colorGrey2));
                txtOngoing.setTextColor(getActivity().getResources().getColor(R.color.colorGrey2));
                break;
            case R.id.txt_ongoing:
                getPendingOrder("Cancle");
                txtTitle.setText("Reject Order");
                txtNeworder.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t2));
                txtComplet.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t2));
                txtOngoing.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t1));

                txtNeworder.setTextColor(getActivity().getResources().getColor(R.color.colorGrey2));
                txtComplet.setTextColor(getActivity().getResources().getColor(R.color.colorGrey2));
                txtOngoing.setTextColor(getActivity().getResources().getColor(R.color.selectcoler));
                break;
            case R.id.txt_complet:
                getPendingOrder("Complete");
                txtTitle.setText("Past Order");

                txtNeworder.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t2));
                txtComplet.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t1));
                txtOngoing.setBackground(getActivity().getResources().getDrawable(R.drawable.border_t2));

                txtNeworder.setTextColor(getActivity().getResources().getColor(R.color.colorGrey2));
                txtComplet.setTextColor(getActivity().getResources().getColor(R.color.selectcoler));
                txtOngoing.setTextColor(getActivity().getResources().getColor(R.color.colorGrey2));
                break;
            default:
                break;
        }
    }

    private void getPendingOrder(String status) {
        custPrograssbar.prograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sid", user.getId());
            jsonObject.put("status", status);
            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().getPending((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {

                Gson gson = new Gson();
                Pending pending = gson.fromJson(result.toString(), Pending.class);
                if (pending.getResult().equalsIgnoreCase("true")) {

                    if (!pending.getOrderHistory().isEmpty()) {
                        txtNodata.setVisibility(View.GONE);
                        recyclePending.setVisibility(View.VISIBLE);
                        pendinglistMain = pending.getOrderHistory();
                        myOrderAdepter = new PendingAdepter(pendinglistMain);
                        recyclePending.setAdapter(myOrderAdepter);
                    }else {
                        txtNodata.setVisibility(View.VISIBLE);
                        txtNodata.setText(""+pending.getResponseMsg());
                        recyclePending.setVisibility(View.GONE);
                    }
                }else {
                    txtNodata.setVisibility(View.VISIBLE);
                    txtNodata.setText(""+pending.getResponseMsg());
                    recyclePending.setVisibility(View.GONE);
                }
            }

        } catch (Exception e) {
            e.toString();
        }
    }



    public class PendingAdepter extends RecyclerView.Adapter<PendingAdepter.ViewHolder> {
        private List<OrderHistoryItem> pendinglist;

        public PendingAdepter(List<OrderHistoryItem> pendinglist) {
            this.pendinglist = pendinglist;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pending_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,
                                     int position) {
            Log.e("position", "" + position);
            OrderHistoryItem order = pendinglist.get(position);
            holder.txtOderid.setText("OrderID #" + order.getId());
            holder.txtStuts.setText(" " + order.getStatus() + " ");
            holder.txtDateandstatus.setText(order.getOrderDate());
            holder.txtAddress.setText("" + order.getCustAdd());
            holder.txtPricetotla.setText(sessionManager.getStringData(curruncy) + " " + order.getTotal());
            holder.lvlClick.setOnClickListener(v -> startActivity(new Intent(getActivity(), OrderActivity.class).putExtra("oid", order.getId())));
        }

        @Override
        public int getItemCount() {
            return pendinglist.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txt_oderid)
            TextView txtOderid;
            @BindView(R.id.txt_pricetotla)
            TextView txtPricetotla;
            @BindView(R.id.txt_dateandstatus)
            TextView txtDateandstatus;
            @BindView(R.id.txt_address)
            TextView txtAddress;
            @BindView(R.id.txt_stuts)
            TextView txtStuts;

            @BindView(R.id.lvl_click)
            LinearLayout lvlClick;


            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
