package com.thilina.treineticpractical.views;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.thilina.treineticpractical.R;
import com.thilina.treineticpractical.adaptors.CategoryAdaptor;
import com.thilina.treineticpractical.adaptors.ProductAdaptor;
import com.thilina.treineticpractical.databinding.ActivityHomeBinding;
import com.thilina.treineticpractical.model.Product;
import com.thilina.treineticpractical.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    ActivityHomeBinding activityHomeBinding;


    private final NavigationBarView.OnItemSelectedListener onItemSelectedListener
            = item -> {
        System.out.println("bottom clicked ");
                Fragment fragment = fragmentMap.get(item.getItemId());
                if (fragment != null) {
                    loadFragment(fragment);
                    return true ;

                }
                return false ;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnItemSelectedListener(onItemSelectedListener);

        fragmentMap.put(R.id.home_fragment, new HomeFragment());
        fragmentMap.put(R.id.chat_fragment, new ChatFragment());

        // Set the default fragment
        loadFragment(fragmentMap.get(R.id.home_fragment));




    }

    private void loadFragment(Fragment fragment ) {


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,   fragment );
        fragmentTransaction.commit();
    }
}