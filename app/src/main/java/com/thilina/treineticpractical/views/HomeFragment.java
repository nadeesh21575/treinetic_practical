package com.thilina.treineticpractical.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thilina.treineticpractical.R;
import com.thilina.treineticpractical.adaptors.CategoryAdaptor;
import com.thilina.treineticpractical.adaptors.ProductAdaptor;
import com.thilina.treineticpractical.databinding.FragmentHomeBinding;
import com.thilina.treineticpractical.model.Category;
import com.thilina.treineticpractical.model.Product;
import com.thilina.treineticpractical.viewmodels.HomeViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    FragmentHomeBinding fragmentHomeBinding;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentHomeBinding.setViewModel(new HomeViewModel());
        fragmentHomeBinding.executePendingBindings();

        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupRecycleVIews();
        super.onViewCreated(view, savedInstanceState);
    }

    //binding product list with recycle view
    @BindingAdapter("data")
    public static void setData(RecyclerView recyclerView, ArrayList<Product> data) {

        ProductAdaptor productAdaptor = new ProductAdaptor(data, recyclerView.getContext());
        recyclerView.setAdapter(productAdaptor);


    }
    //binding category list with recycle view
    @BindingAdapter("categoryData")
    public static void setCatData(RecyclerView recyclerView, ArrayList<Category> data) {

        CategoryAdaptor categoryAdaptor = new CategoryAdaptor(data, recyclerView.getContext());
        recyclerView.setAdapter(categoryAdaptor);


    }


    public void setupRecycleVIews() {

        // setup category recycle view
        System.out.println("loadData " + fragmentHomeBinding.getViewModel().getCategoryList().size());
        LinearLayoutManager catRecLinearLayout = new LinearLayoutManager(getActivity());
        catRecLinearLayout.setOrientation(RecyclerView.HORIZONTAL);
        fragmentHomeBinding.rcViewCategory.setLayoutManager(catRecLinearLayout);
        fragmentHomeBinding.rcViewCategory.setHasFixedSize(true);

        // setup product recycle view
        LinearLayoutManager productRecLinearLayout = new LinearLayoutManager(getActivity());
        productRecLinearLayout.setOrientation(RecyclerView.HORIZONTAL);
        fragmentHomeBinding.rcViewProduct.setLayoutManager(productRecLinearLayout);
        fragmentHomeBinding.rcViewProduct.setHasFixedSize(true);
        System.out.println("loadData product " + fragmentHomeBinding.getViewModel().getProductList().size());


    }

}