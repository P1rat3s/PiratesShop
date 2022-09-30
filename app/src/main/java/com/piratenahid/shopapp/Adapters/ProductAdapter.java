package com.piratenahid.shopapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.piratenahid.shopapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context mContext;
    List<List<String>> list;

    public ProductAdapter(Context mContext, List<List<String>> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.products_recycler_layout, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(list.get(0).get(position)).into(holder.image);
        holder.product_name.setText(list.get(1).get(position));
        holder.product_price.setText("Tk."+list.get(2).get(position));


    }

    @Override
    public int getItemCount() {
        return list.get(0).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView product_name, product_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name_text);
            product_price = itemView.findViewById(R.id.product_price_text);


        }


    }
}
