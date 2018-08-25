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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.activities.LoginActivity;
import com.competiton.pregnancy.pregnancyapp.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends DialogFragment implements View.OnClickListener{

    private Button btnViewPrescription, btnSubmit;
    private EditText etPrescription;
    private TextView tvPrescription, tvHeading, tvContent, tvHeading2, tvContent2;
    private ImageView ivWeek;
    private LinearLayout lrPrescription;
    private Spinner spinnerPregnancyPeriod;
    private RelativeLayout rlSignOut;
    private Toolbar toolbar;
    private SharedPrefs sharedPrefs;
    private String weekPregnancyPeriod;
    private Boolean showPrescription = true;

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
        tvHeading = view.findViewById(R.id.tvHeading);
        tvContent = view.findViewById(R.id.tvContent);
        tvHeading2 = view.findViewById(R.id.tvHeading2);
        tvContent2 = view.findViewById(R.id.tvContent2);
        ivWeek = view.findViewById(R.id.ivWeek);
        setSpinner();
        return view;
    }

    private void setSpinner() {
        List<String> weekPregnancyList = new ArrayList<>();
        weekPregnancyList.add("0 - 8 week");
        weekPregnancyList.add("9 - 17 week");
        weekPregnancyList.add("18 - 26 week");
        weekPregnancyList.add("27 - 36 week");

        ArrayAdapter<String> dataAdapterWeekPregnancy = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, weekPregnancyList);
        dataAdapterWeekPregnancy.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerPregnancyPeriod.setAdapter(dataAdapterWeekPregnancy);

        spinnerPregnancyPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weekPregnancyPeriod = parent.getItemAtPosition(position).toString().trim();
                ivWeek.setVisibility(View.VISIBLE);
                tvContent.setVisibility(View.VISIBLE);
                tvHeading.setVisibility(View.VISIBLE);
                tvHeading2.setVisibility(View.VISIBLE);
                tvContent2.setVisibility(View.VISIBLE);
                tvHeading.setText("Eating well: "+weekPregnancyPeriod);
                tvContent.setText(getString(R.string.week1));
                switch(position){
                    case 0:
                        Glide.with(getContext()).load(R.drawable.pic1).into(ivWeek);
                        break;
                    case 1:
                        Glide.with(getContext()).load(R.drawable.pic2).into(ivWeek);
                        break;
                    case 2:
                        Glide.with(getContext()).load(R.drawable.pic3).into(ivWeek);
                        break;
                    case 3:
                        Glide.with(getContext()).load(R.drawable.pic4).into(ivWeek);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ivWeek.setVisibility(View.GONE);
                tvContent.setVisibility(View.GONE);
                tvHeading.setVisibility(View.GONE);
                tvContent2.setVisibility(View.GONE);
                tvHeading2.setVisibility(View.GONE);
            }
        });

    }

    private void initView(View view){
        tvPrescription = view.findViewById(R.id.tvPrescription);
        rlSignOut = view.findViewById(R.id.rlSignOut);
        toolbar = view.findViewById(R.id.profile_toolbar);
        btnViewPrescription = view.findViewById(R.id.btnViewPrescription);
        spinnerPregnancyPeriod = view.findViewById(R.id.pregnancy_period_list);
        btnViewPrescription.setOnClickListener(this);
        rlSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnViewPrescription:
                if(showPrescription){
                    showPrescription = false;
                    tvPrescription.setVisibility(View.VISIBLE);
                    btnViewPrescription.setText("Hide Prescription");
                }
                else{
                    showPrescription = true;
                    tvPrescription.setVisibility(View.GONE);
                    btnViewPrescription.setText("View Prescription");
                }
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
