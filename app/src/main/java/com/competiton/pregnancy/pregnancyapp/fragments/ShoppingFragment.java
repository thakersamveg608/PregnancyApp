package com.competiton.pregnancy.pregnancyapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.adapter.ShopAdapter;
import com.competiton.pregnancy.pregnancyapp.model.PregaShopDetails;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends DialogFragment {

    private RecyclerView rvShop;
    private ShopAdapter shopAdapter;
    private List<PregaShopDetails> pregaShopDetailsList = new ArrayList<>();

    public ShoppingFragment() {
        // Required empty public constructor
    }

    public static ShoppingFragment newInstance() {
        ShoppingFragment fragment = new ShoppingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null && d.getWindow() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        rvShop = view.findViewById(R.id.rvShop);

        PregaShopDetails pregaShopDetails = new PregaShopDetails("Palmer's Massage Lotion", "https://rukminim1.flixcart.com/image/832/832/j52rrm80/moisturizer-cream/e/f/e/250-massae-lotion-for-stretch-marks-palmer-s-original-imaevfxx3scbgmeh.jpeg?q=70");
        pregaShopDetailsList.add(pregaShopDetails);
        pregaShopDetailsList.add(pregaShopDetails);
        pregaShopDetailsList.add(pregaShopDetails);
        pregaShopDetailsList.add(pregaShopDetails);
        pregaShopDetailsList.add(pregaShopDetails);
        pregaShopDetailsList.add(pregaShopDetails);
        shopAdapter = new ShopAdapter(getContext(), pregaShopDetailsList);
        rvShop.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvShop.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
