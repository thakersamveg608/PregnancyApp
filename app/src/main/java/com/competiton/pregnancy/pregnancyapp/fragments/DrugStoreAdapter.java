package com.competiton.pregnancy.pregnancyapp.fragments;

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
import com.competiton.pregnancy.pregnancyapp.model.DrugStoreDetail;

import java.util.ArrayList;
import java.util.List;

public class DrugStoreAdapter extends RecyclerView.Adapter<DrugStoreAdapter.ViewHolder>{

    private List<DrugStoreDetail> drugStoreDetailList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public DrugStoreAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setDrugStoreDetailList(List<DrugStoreDetail> drugStoreDetailList) {
        this.drugStoreDetailList = drugStoreDetailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.drug_store_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DrugStoreDetail drugStoreDetailItem = drugStoreDetailList.get(position);
        holder.tvItemDrugStore.setText(drugStoreDetailItem.getDrugName());
        Glide.with(context).load(drugStoreDetailItem.getDrugImage()).into(holder.ivItemDrugStore);
    }

    @Override
    public int getItemCount() {
        return drugStoreDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItemDrugStore;
        TextView tvItemDrugStore;

        public ViewHolder(View itemView) {
            super(itemView);
            ivItemDrugStore = itemView.findViewById(R.id.ivItemDrugStore);
            tvItemDrugStore = itemView.findViewById(R.id.tvItemDrugStore);
        }
    }


}
