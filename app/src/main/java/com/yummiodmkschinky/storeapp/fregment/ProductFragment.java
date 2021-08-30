package com.yummiodmkschinky.storeapp.fregment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yummiodmkschinky.storeapp.R;
import com.yummiodmkschinky.storeapp.activity.ProductActivity;
import com.yummiodmkschinky.storeapp.model.Product;
import com.yummiodmkschinky.storeapp.model.ProductDataItem;
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
import retrofit2.Call;

import static com.yummiodmkschinky.storeapp.retrofit.APIClient.baseUrl;
import static com.yummiodmkschinky.storeapp.utils.SessionManager.curruncy;
import static com.yummiodmkschinky.storeapp.utils.Utiles.updatestatus;


public class ProductFragment extends Fragment implements GetResult.MyListener{
    @BindView(R.id.txt_itmecount)
    TextView txtItmecount;
    @BindView(R.id.recycle_pending)
    RecyclerView recyclePending;




    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    Store user;
    @BindView(R.id.txtNodata)
    TextView txtNodata;
    List<ProductDataItem> pendinglistMain = new ArrayList<>();
    ProductAdepter myOrderAdepter;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);

        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());
        user = sessionManager.getUserDetails("");
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recyclePending.setLayoutManager(recyclerLayoutManager);
        myOrderAdepter = new ProductAdepter(pendinglistMain);
        recyclePending.setAdapter(myOrderAdepter);
        getProduct();

        return view;
    }

    private void getProduct() {
        custPrograssbar.prograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sid", user.getId());
            JsonParser jsonParser = new JsonParser();
            Call<JsonObject> call = APIClient.getInterface().getTotalProduct((JsonObject) jsonParser.parse(jsonObject.toString()));
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
                Product product = gson.fromJson(result.toString(), Product.class);
                if (product.getResult().equalsIgnoreCase("true")) {
                    txtItmecount.setText(product.getProductData().size() + " Products");
                    if (product.getProductData().isEmpty()) {
                        txtNodata.setVisibility(View.VISIBLE);
                        recyclePending.setVisibility(View.GONE);
                    } else {
                        pendinglistMain = product.getProductData();
                        myOrderAdepter = new ProductAdepter(pendinglistMain);
                        recyclePending.setAdapter(myOrderAdepter);
                    }
                } else {
                    txtNodata.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public class ProductAdepter extends RecyclerView.Adapter<ProductAdepter.ViewHolder> {
        private List<ProductDataItem> productDataItems;

        public ProductAdepter(List<ProductDataItem> pendinglist) {
            this.productDataItems = pendinglist;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,
                                     int position) {
            Log.e("position", "" + position);
            ProductDataItem productItem = productDataItems.get(position);
            holder.txtTitle.setText("" + productItem.getProductName());
            holder.txtCategory.setText("" + productItem.getProductCategory());


            holder.txtPrice.setText(sessionManager.getStringData(curruncy) + productItem.getProductPrice());

            if (productItem.getIsVeg().equalsIgnoreCase("0")) {
                holder.txtPtype.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_nonveg));
            } else if (productItem.getIsVeg().equalsIgnoreCase("1")) {

                holder.txtPtype.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_veg));

            } else if (productItem.getIsVeg().equalsIgnoreCase("2")) {
                holder.txtPtype.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_egg));
            }
            Glide.with(getActivity()).load(baseUrl + productItem.getProductImage()).placeholder(R.drawable.slider).into(holder.imgIcon);
            holder.lvlClicl.setOnClickListener(view -> startActivity(new Intent(getActivity(), ProductActivity.class).putExtra("MyClass", productItem).putParcelableArrayListExtra("MyList", productItem.getAddondata())));
        }

        @Override
        public int getItemCount() {
            return productDataItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_icon)
            ImageView imgIcon;
            @BindView(R.id.txt_title)
            TextView txtTitle;

            @BindView(R.id.txt_price)
            TextView txtPrice;
            @BindView(R.id.lvl_clicl)
            LinearLayout lvlClicl;

            @BindView(R.id.txt_ptype)
            ImageView txtPtype;
            @BindView(R.id.txt_category)
            TextView txtCategory;

            @BindView(R.id.rlt_detail)
            RelativeLayout rltDetail;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (updatestatus) {
            updatestatus = false;
            getProduct();
        }
    }
}
