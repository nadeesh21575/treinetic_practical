package com.thilina.treineticpractical.viewmodels;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thilina.treineticpractical.BR;
import com.thilina.treineticpractical.MyApplication;
import com.thilina.treineticpractical.interfaces.NetworkCallback;
import com.thilina.treineticpractical.model.Category;
import com.thilina.treineticpractical.model.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeViewModel extends BaseObservable {
    @Bindable
    private ArrayList<Category> categoryList = new ArrayList<>();
    @Bindable
    private ArrayList<Product> productList = new ArrayList<>();


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String basUrl = "https://59b8726e92ccc3eb44b0c193eeef96f6.m.pipedream.net/";
    private String TAG = "HomeViewModel";

    public HomeViewModel() {
        loadCategories();
        loadProducts();


    }

    private void loadProducts() {
        System.out.println("getProductList size  0 " + productList.size());

        NetworkCallback callback = productRespond -> {
            System.out.println("respond callback  size  " + productRespond);


            if (productRespond != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Product>>() {
                }.getType();
                List<Product> tmpList = gson.fromJson(productRespond, type);
                productList.addAll(tmpList);
                System.out.println("getProductList size  1 " + productList.toString());
                notifyPropertyChanged(BR.productList);

            }
        };

        getData("products", callback);


    }

    private void loadCategories() {
        System.out.println("loadCategories ");
        categoryList.addAll(Arrays.asList(new Category("All Products", true), new Category("Popular", false)
                , new Category("Recent", false), new Category("Trending", false), new Category("Saved",
                        false), new Category("Rated", false)));
        notifyPropertyChanged(BR.categoryList);

        System.out.println("loadCategories " + categoryList.size());

    }

    @Bindable
    public ArrayList<Category> getCategoryList() {
        notifyPropertyChanged(BR.categoryList);

        return categoryList;
    }

    @Bindable
    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Bindable
    public ArrayList<Product> getProductList() {
        System.out.println("getProductList() size  " + productList.size());
        return productList;
    }

    @Bindable
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Nullable
    private void getData(String subUrl, NetworkCallback callback) {

        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());


        mStringRequest = new StringRequest(Request.Method.GET, basUrl + subUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("onResponse size  " + response);
                callback.onDataReceived(response);


            }
        }, error -> Log.i(TAG, "Error :" + error.toString()));

        mRequestQueue.add(mStringRequest);

    }
}
