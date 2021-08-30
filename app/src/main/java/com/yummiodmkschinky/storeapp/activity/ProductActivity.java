package com.yummiodmkschinky.storeapp.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yummiodmkschinky.storeapp.R;
import com.yummiodmkschinky.storeapp.model.AddonItem;
import com.yummiodmkschinky.storeapp.model.Addondata;
import com.yummiodmkschinky.storeapp.model.ProductDataItem;
import com.yummiodmkschinky.storeapp.model.RestResponse;
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
import retrofit2.Call;

import static com.yummiodmkschinky.storeapp.utils.Utiles.updatestatus;

public class ProductActivity extends AppCompatActivity implements GetResult.MyListener {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_disc)
    TextView txtDisc;
    @BindView(R.id.img_product)
    ImageView imgProduct;
    @BindView(R.id.img_isvage)
    ImageView imgIsvage;
    @BindView(R.id.package_lst)
    RecyclerView packageLst;

    ArrayList<Addondata> productInfoItems;
    ProductDataItem dataItem;
    SessionManager sessionManager;
    CustPrograssbar custPrograssbar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// app icon in action bar clicked; go home
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product Details");
        getSupportActionBar().setElevation(0f);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(ProductActivity.this);

        dataItem = (ProductDataItem) getIntent().getParcelableExtra("MyClass");
        productInfoItems = getIntent().getParcelableArrayListExtra("MyList");

        txtTitle.setText("" + dataItem.getProductName());
        txtDisc.setText(dataItem.getShortDesc());
        Glide.with(ProductActivity.this).load(APIClient.baseUrl + "/" + dataItem.getProductImage()).placeholder(R.drawable.slider).into(imgProduct);
        if (dataItem.getIsVeg().equalsIgnoreCase("0")) {
            imgIsvage.setImageDrawable(getResources().getDrawable(R.drawable.ic_nonveg));
        } else if (dataItem.getIsVeg().equalsIgnoreCase("1")) {

            imgIsvage.setImageDrawable(getResources().getDrawable(R.drawable.ic_veg));

        } else if (dataItem.getIsVeg().equalsIgnoreCase("2")) {
            imgIsvage.setImageDrawable(getResources().getDrawable(R.drawable.ic_egg));
        }

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        packageLst.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(packageLst.getContext(),
                        recyclerLayoutManager.getOrientation());
        packageLst.addItemDecoration(dividerItemDecoration);

        PackageRecyclerViewAdapter recyclerViewAdapter = new
                PackageRecyclerViewAdapter(productInfoItems, this);
        packageLst.setAdapter(recyclerViewAdapter);


    }



    public static class PackageRecyclerViewAdapter extends
            RecyclerView.Adapter<PackageRecyclerViewAdapter.ViewHolder> {
        SessionManager sessionManager;

        private List<Addondata> packageList;
        private Context context;


        public PackageRecyclerViewAdapter(List<Addondata> packageListIn
                , Context ctx) {
            packageList = packageListIn;
            context = ctx;
            sessionManager = new SessionManager(context);

        }

        @Override
        public PackageRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.package_item, parent, false);

            PackageRecyclerViewAdapter.ViewHolder viewHolder =
                    new PackageRecyclerViewAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(PackageRecyclerViewAdapter.ViewHolder holder,
                                     int position) {
            Addondata packageModel = packageList.get(position);
            if (packageModel.getAddonLimit() == 0) {
                holder.packageName.setText(packageModel.getTitle());
            } else {
                holder.packageName.setText(packageModel.getTitle() + "(" + packageModel.getAddonLimit() + ")");

            }

            if (packageModel.getAddonIsRadio() == 1) {

                for (AddonItem item : packageModel.getAddonItemData()) {
                    TextView rb = new TextView(PackageRecyclerViewAdapter.this.context);
                    rb.setPadding(5,5,5,5);
                    rb.setId(Integer.parseInt(item.getId()));
                    rb.setText(item.getTitle() + " " + sessionManager.getStringData(SessionManager.curruncy) + item.getPrice());
                    rb.setTextSize(14);
                    holder.priceGroup.addView(rb);
                }
            } else {
                for (AddonItem item : packageModel.getAddonItemData()) {
                    TextView box = new TextView(PackageRecyclerViewAdapter.this.context);
                    box.setId(Integer.parseInt(item.getId()));
                    box.setPadding(5,5,5,5);
                    box.setText(item.getTitle() + "  " + sessionManager.getStringData(SessionManager.curruncy) + item.getPrice());
                    box.setTextSize(14);
                    holder.lvlChackbox.addView(box);

                }
            }
            holder.priceGroup.setOnCheckedChangeListener((radioGroup, i) -> {


                View radioButton = holder.priceGroup.findViewById(radioGroup.getCheckedRadioButtonId());

                int idx = holder.priceGroup.indexOfChild(radioButton);
                for (int a = 0; a < packageModel.getAddonItemData().size(); a++) {

                    packageModel.getAddonItemData().get(a).setSelect(false);
                }
                packageModel.getAddonItemData().get(idx).setSelect(true);

            });


        }

        @Override
        public int getItemCount() {
            return packageList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView packageName;
            public RadioGroup priceGroup;
            public LinearLayout lvlChackbox;


            public ViewHolder(View view) {
                super(view);
                packageName = view.findViewById(R.id.package_name);
                priceGroup = view.findViewById(R.id.price_grp);
                lvlChackbox = view.findViewById(R.id.lvl_chackbox);


            }
        }
    }


    private void productstatus(String id, String status) {
        custPrograssbar.prograssCreate(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("status", status);
            JsonParser jsonParser = new JsonParser();
            Call<JsonObject> call = APIClient.getInterface().ProductStatus((JsonObject) jsonParser.parse(jsonObject.toString()));
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
                RestResponse response = gson.fromJson(result.toString(), RestResponse.class);

                if (response.getResult().equalsIgnoreCase("true")) {
                    Toast.makeText(ProductActivity.this, response.getResponseMsg(), Toast.LENGTH_LONG).show();
                    updatestatus = true;
                    finish();
                }
            }
        } catch (Exception e) {
            e.toString();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product, menu);
        MenuItem item = menu.findItem(R.id.myswitch);
        item.setActionView(R.layout.switch_layout);
        Switch mySwitch = item.getActionView().findViewById(R.id.switchForActionBar);
        mySwitch.setChecked(dataItem.getProductStatus() != 0);
        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // do something based on isChecked
            int a = 0;
            if (isChecked) {
                a = 1;
            } else {
                a = 0;
            }
            productstatus(dataItem.getId(), String.valueOf(a));
        });

        return true;
    }
}