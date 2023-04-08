
package com.thilina.treineticpractical.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thilina.treineticpractical.BR;
import com.thilina.treineticpractical.interfaces.CustomClickListenerProduct;
import com.thilina.treineticpractical.R;
import com.thilina.treineticpractical.databinding.ProductCardBinding;

import java.util.List;

import com.thilina.treineticpractical.model.Product;
import com.thilina.treineticpractical.views.ViewProductActivity;


public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> implements CustomClickListenerProduct {

    private List<Product> productModelList;

    private Context context;

    public ProductAdaptor(List<Product> productModelList ,Context context)   {

        this.productModelList = productModelList;
        this.context = context;
    }

    @Override
    public ProductAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        ProductCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_card, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product dataModel = productModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setCustomClickListenerProduct(this);
    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    @Override
    public void cardClicked(Product f) {
        Intent intent = new Intent(context, ViewProductActivity.class);

        context.startActivity(intent);


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ProductCardBinding itemRowBinding;

        public ViewHolder(ProductCardBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.product, obj);
            itemRowBinding.executePendingBindings();
        }
    }
    //to load network image to imageview
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String link) {

        System.out.println("setImage "+link);
        if (link != null && !link.isEmpty()) {
            Glide.with(imageView.getContext())
                    .load(link)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }


}