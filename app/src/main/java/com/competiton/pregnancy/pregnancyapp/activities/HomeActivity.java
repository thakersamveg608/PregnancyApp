package com.competiton.pregnancy.pregnancyapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.competiton.pregnancy.pregnancyapp.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivHealthCentre, ivCustomerImage;
    private CardView cvShop, cvCustomerCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        ivHealthCentre = findViewById(R.id.ivHealthCentre);
        ivCustomerImage = findViewById(R.id.ivCustomerImage);
        cvShop = findViewById(R.id.cvShop);
        cvCustomerCare = findViewById(R.id.cvCustomerCare);

        Glide.with(this).load(R.drawable.health_centre).apply(RequestOptions.circleCropTransform()).into(ivHealthCentre);
        Glide.with(this).load(R.drawable.ic_profile_image).apply(RequestOptions.circleCropTransform()).into(ivCustomerImage);
        ivHealthCentre.setOnClickListener(this);
        cvShop.setOnClickListener(this);
        cvCustomerCare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHealthCentre:
                Intent intent = new Intent(HomeActivity.this, HealthCentreActivity.class);
                startActivity(intent);
                break;
            case R.id.cvShop:
                break;
            case R.id.cvCustomerCare:
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+919893302615"));
                startActivity(i);
                break;
        }
    }
}
