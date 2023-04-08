package com.thilina.treineticpractical.viewmodels;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thilina.treineticpractical.BR;
import com.thilina.treineticpractical.MyApplication;
import com.thilina.treineticpractical.interfaces.NetworkCallback;
import com.thilina.treineticpractical.model.Product;

import java.lang.reflect.Type;

public class ViewProductViewModel extends BaseObservable {

    @Bindable
    private Product product ;


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String basUrl = "https://59b8726e92ccc3eb44b0c193eeef96f6.m.pipedream.net/";
    private String TAG = "ViewProductViewModel";

    public ViewProductViewModel() {

        fetchProduct();


    }

    private void fetchProduct() {


        NetworkCallback callback = productRespond -> {
            System.out.println("respond callback  size  " + productRespond);


            if (productRespond != null) {
                Gson gson = new Gson();
                Type type = new TypeToken< Product>() {
                }.getType();
                product = gson.fromJson(productRespond, type);

                System.out.println("getProductList size  1 " + product.toString());
                notifyPropertyChanged(BR.product);

            }
        };

        getData("featured",callback);





    }
    @Bindable
    public Product getProduct() {
        return product;
    }

    @Bindable
    public void setProduct(Product product) {
        this.product = product;
    }


    @Nullable
    private void getData(String subUrl , NetworkCallback callback) {

        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());


        mStringRequest = new StringRequest(Request.Method.GET, basUrl + subUrl, response -> {
            System.out.println("onResponse size  " + response);
            callback.onDataReceived(response);


        }, error -> Log.i(TAG, "Error :" + error.toString()));

        mRequestQueue.add(mStringRequest);

    }
}

