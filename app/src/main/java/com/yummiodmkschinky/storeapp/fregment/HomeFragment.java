package com.yummiodmkschinky.storeapp.fregment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yummiodmkschinky.storeapp.R;
import com.yummiodmkschinky.storeapp.adepter.Homeadepter;
import com.yummiodmkschinky.storeapp.model.HomeStore;
import com.yummiodmkschinky.storeapp.model.Store;
import com.yummiodmkschinky.storeapp.retrofit.APIClient;
import com.yummiodmkschinky.storeapp.retrofit.GetResult;
import com.yummiodmkschinky.storeapp.utils.CustPrograssbar;
import com.yummiodmkschinky.storeapp.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class HomeFragment extends Fragment implements GetResult.MyListener {


    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_itmecount)
    TextView txtItmecount;



    @BindView(R.id.recycle_home)
    RecyclerView recycleHome;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    Store store;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());
        store = sessionManager.getUserDetails("");
        txtName.setText("Hello "+store.getName());
        txtItmecount.setText(""+store.getMobile());

        recycleHome.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        getDesbord();
        return view;
    }

    private void getDesbord() {
        custPrograssbar.prograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sid", store.getId());
            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().getDesbord((JsonObject) jsonParser.parse(jsonObject.toString()));
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
                HomeStore homeStore = gson.fromJson(result.toString(), HomeStore.class);
                if (homeStore.getResult().equalsIgnoreCase("true")) {
                    Homeadepter homeadepter = new Homeadepter(homeStore.getStoreReportData(), getActivity());
                    recycleHome.setAdapter(homeadepter);
                }
            }
        } catch (Exception e) {
            Log.e("eror", "-->" + e.toString());

        }
    }
}