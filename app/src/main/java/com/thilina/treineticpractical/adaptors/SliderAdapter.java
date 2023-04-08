package com.thilina.treineticpractical.adaptors;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.thilina.treineticpractical.R;


  //this is for load product view image slide
  public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    private String[] images;

    public SliderAdapter(String[] images) {
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_slider_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Glide.with(viewHolder.itemView)
                .load(images[position])
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    static class ViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post_img);
        }
    }
}