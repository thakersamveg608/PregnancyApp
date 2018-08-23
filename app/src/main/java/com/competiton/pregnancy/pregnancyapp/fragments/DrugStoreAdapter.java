package com.competiton.pregnancy.pregnancyapp.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.competiton.pregnancy.pregnancyapp.R;

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

    }

    @Override
    public int getItemCount() {
        return drugStoreDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
