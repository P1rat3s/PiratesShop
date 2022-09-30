package com.piratenahid.shopapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.piratenahid.shopapp.R;

import java.util.List;

public class BrandHighLightAdapter extends RecyclerView.Adapter<BrandHighLightAdapter.ViewHolder>{
    public BrandHighLightAdapter(Context mContext, List<List<String>> list) {
        this.mContext = mContext;
        this.list = list;
    }

    Context mContext;
    List<List<String>> list;


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image1,image2,image3,image4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.brand_image1);
            image2 = itemView.findViewById(R.id.brand_image2);
            image3 = itemView.findViewById(R.id.brand_image3);
            image4 = itemView.findViewById(R.id.brand_image4);

        }


    }

    @NonNull
    @Override
    public BrandHighLightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.brand_highlight_layout, parent, false);
        return new BrandHighLightAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandHighLightAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(list.get(0).get(position)).into(holder.image1);
        Glide.with(mContext).load(list.get(1).get(position)).into(holder.image2);
        Glide.with(mContext).load(list.get(2).get(position)).into(holder.image3);
        Glide.with(mContext).load(list.get(3).get(position)).into(holder.image4);

    }

    @Override
    public int getItemCount() {
        return list.get(0).size();
    }
}
