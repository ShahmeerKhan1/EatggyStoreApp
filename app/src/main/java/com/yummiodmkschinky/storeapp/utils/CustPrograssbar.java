package com.yummiodmkschinky.storeapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class CustPrograssbar {

    ProgressDialog progressDialog;

    public void prograssCreate(Context context) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                return;
            } else {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Progress...");
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closePrograssBar() {
        if (progressDialog != null) {
            try {
                progressDialog.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
