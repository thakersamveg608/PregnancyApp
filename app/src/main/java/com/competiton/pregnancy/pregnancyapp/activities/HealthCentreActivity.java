package com.competiton.pregnancy.pregnancyapp.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.fragments.BloodBankFragment;
import com.competiton.pregnancy.pregnancyapp.fragments.HospitalFragment;

public class HealthCentreActivity extends AppCompatActivity {

    private ImageView ivHospital, ivBloodBank, ivDrugStore;
    private CardView cvHospital, cvBloodBank, cvDrugStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_centre);
        initView();

        cvHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                HospitalFragment hospitalFragment = HospitalFragment.newInstance();
                hospitalFragment.show(fm, "Hospital Fragment");
            }
        });

        cvBloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                BloodBankFragment bloodBankFragment = BloodBankFragment.newInstance();
                bloodBankFragment.show(fm, "BloodBank Fragment");
            }
        });

        cvDrugStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView(){

        ivHospital = findViewById(R.id.ivHospital);
        ivBloodBank = findViewById(R.id.ivBloodBank);
        ivDrugStore = findViewById(R.id.ivDrugStore);
        cvHospital = findViewById(R.id.cvHospital);
        cvBloodBank = findViewById(R.id.cvBloodBank);
        cvDrugStore = findViewById(R.id.cvDrugStore);

        Glide.with(this).load(R.drawable.ic_hospital).into(ivHospital);
        Glide.with(this).load(R.drawable.ic_blood).into(ivBloodBank);
        Glide.with(this).load(R.drawable.ic_drug_store).into(ivDrugStore);
    }


}
