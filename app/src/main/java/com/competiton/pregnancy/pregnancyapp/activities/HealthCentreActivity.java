package com.competiton.pregnancy.pregnancyapp.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.fragments.HospitalFragment;

public class HealthCentreActivity extends AppCompatActivity {

    private ImageView ivHospital, ivBloodBank, ivDrugStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_centre);
        initView();

        ivHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                HospitalFragment hospitalFragment = HospitalFragment.newInstance();
                hospitalFragment.show(fm, "Hospital Fragment");
            }
        });
    }

    private void initView(){

        ivHospital = findViewById(R.id.ivHospital);
        ivBloodBank = findViewById(R.id.ivBloodBank);
        ivDrugStore = findViewById(R.id.ivDrugStore);

        Glide.with(this).load(R.drawable.ic_hospital).into(ivHospital);
        Glide.with(this).load(R.drawable.ic_blood).into(ivBloodBank);
        Glide.with(this).load(R.drawable.ic_drug_store).into(ivDrugStore);
    }


}
