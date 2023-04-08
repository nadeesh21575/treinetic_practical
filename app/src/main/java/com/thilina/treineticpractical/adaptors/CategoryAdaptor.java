package com.thilina.treineticpractical.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

import com.thilina.treineticpractical.BR;
import com.thilina.treineticpractical.interfaces.CustomClickListenerCat;
import com.thilina.treineticpractical.R;
import com.thilina.treineticpractical.model.Category;

import java.util.List;

import com.thilina.treineticpractical.databinding.CategoryCardBinding;


public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> implements CustomClickListenerCat {

    private List<Category> categoryModelList;
    private Context context;

    public CategoryAdaptor(List<Category> categoryModelList, Context ctx) {

        this.categoryModelList = categoryModelList;
        context = ctx;
    }

    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        CategoryCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.category_card, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category dataModel = categoryModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setCustomClickListenerCat(this);
    }


    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    @Override
    public void cardClicked(Category f) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CategoryCardBinding itemRowBinding;

        public ViewHolder(CategoryCardBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.category, obj);
            itemRowBinding.executePendingBindings();
        }
    }

}