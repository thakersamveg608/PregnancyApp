package com.competiton.pregnancy.pregnancyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.model.PregaShopDetails;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    Context context;
    List<PregaShopDetails> shopItems = new ArrayList<>();

    public ShopAdapter(Context context, List<PregaShopDetails> shopItems){

        this.context = context;
        this.shopItems = shopItems;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prega_shop, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PregaShopDetails pregaShopDetails = shopItems.get(position);
        holder.tvItem.setText(pregaShopDetails.getItemName());
        Glide.with(context).load(pregaShopDetails.getItemUrl()).into(holder.ivItem);

    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvItem;
        public ImageView ivItem;


        public ViewHolder(View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tvItem);
            ivItem = itemView.findViewById(R.id.ivItem);
        }
    }
}
