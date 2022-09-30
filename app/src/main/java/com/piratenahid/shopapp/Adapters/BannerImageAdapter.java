package com.piratenahid.shopapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.piratenahid.shopapp.R;

import java.util.List;

public class BannerImageAdapter extends RecyclerView.Adapter<BannerImageAdapter.ViewHolder> {
    Context mContext;
    List<String> list;

    public BannerImageAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.banner_imageView);

        }


    }



    @NonNull
    @Override
    public BannerImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_recycle_layout, parent, false);
        return new BannerImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerImageAdapter.ViewHolder holder, int position) {
        Log.e("image", list.get(position));
        Glide.with(mContext).load(list.get(position)).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
