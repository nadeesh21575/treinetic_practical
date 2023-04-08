package com.thilina.treineticpractical.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.thilina.treineticpractical.R;

import com.smarteist.autoimageslider.SliderView;
import com.thilina.treineticpractical.adaptors.ProductAdaptor;
import com.thilina.treineticpractical.adaptors.SliderAdapter;
import com.thilina.treineticpractical.databinding.ActivirtyViewProductBinding;
import com.thilina.treineticpractical.generated.callback.OnClickListener;
import com.thilina.treineticpractical.model.Product;
import com.thilina.treineticpractical.viewmodels.HomeViewModel;
import com.thilina.treineticpractical.viewmodels.ViewProductViewModel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ViewProductActivity extends AppCompatActivity {

    ActivirtyViewProductBinding activirtyViewProductBinding;
    private SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activirtyViewProductBinding = DataBindingUtil.setContentView(this, R.layout.activirty_view_product);
        activirtyViewProductBinding.setViewModel(new ViewProductViewModel());
        activirtyViewProductBinding.executePendingBindings();


        // set listener to back button
        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });

    }

    @BindingAdapter("images")
    public static void setData(SliderView sliderView, Product product) {
        if (product == null || product.getImages() == null) {
            return;
        }

        String[] imageList = {product.getImages(), product.getImages()};


        sliderView.setSliderAdapter(new SliderAdapter(imageList));
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(ContextCompat.getColor(sliderView.getContext(), R.color.grey_shade));
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);

    }


}