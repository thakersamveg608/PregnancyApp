package com.competiton.pregnancy.pregnancyapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.activities.LoginActivity;
import com.competiton.pregnancy.pregnancyapp.utils.SharedPrefs;

public class ProfileFragment extends DialogFragment implements View.OnClickListener{

    private Button btnEditPrescription, btnViewPrescription, btnSubmit;
    private EditText etPrescription;
    private TextView tvPrescription;
    private LinearLayout lrPrescription;
    private RelativeLayout rlSignOut;
    private Toolbar toolbar;
    private SharedPrefs sharedPrefs;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        sharedPrefs = new SharedPrefs(getContext());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_expand_more));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment.this.dismiss();
            }
        });
        return view;
    }

    private void initView(View view){
        lrPrescription = view.findViewById(R.id.lrPrescription);
        tvPrescription = view.findViewById(R.id.tvPrescription);
        etPrescription = view.findViewById(R.id.etPrescription);
        rlSignOut = view.findViewById(R.id.rlSignOut);
        toolbar = view.findViewById(R.id.profile_toolbar);
        btnViewPrescription = view.findViewById(R.id.btnViewPrescription);
        btnEditPrescription = view.findViewById(R.id.btnEditPrescription);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        btnEditPrescription.setOnClickListener(this);
        btnViewPrescription.setOnClickListener(this);
        rlSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                if(!TextUtils.isEmpty(etPrescription.getText().toString().trim())){
                    sharedPrefs.setPrescription(etPrescription.getText().toString().trim());
                    lrPrescription.setVisibility(View.GONE);
                }
                break;
            case R.id.btnViewPrescription:
                if(!TextUtils.isEmpty(sharedPrefs.getPrescription())){
                    tvPrescription.setText(sharedPrefs.getPrescription());
                }
                else{
                    tvPrescription.setText("No prescription available yet.");
                }
                tvPrescription.setVisibility(View.VISIBLE);
                lrPrescription.setVisibility(View.GONE);
                break;
            case R.id.btnEditPrescription:
                lrPrescription.setVisibility(View.VISIBLE);
                tvPrescription.setVisibility(View.GONE);
                break;
            case R.id.rlSignOut:
                sharedPrefs.setLogin(false);
                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
        }
    }
}