package com.competiton.pregnancy.pregnancyapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.competiton.pregnancy.pregnancyapp.HomeActivity;
import com.competiton.pregnancy.pregnancyapp.R;

public class SplashActivity extends AppCompatActivity {

    private Button btnEnglish, btnHindi;
    private RelativeLayout rlProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btnEnglish = findViewById(R.id.btnEnglish);
        btnHindi = findViewById(R.id.btnHindi);
        rlProceed = findViewById(R.id.rlProceed);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHindi.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner_alternate));
                btnEnglish.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner));
                btnHindi.setTextColor(getResources().getColor(R.color.pink_high));
                btnEnglish.setTextColor(getResources().getColor(R.color.white));
            }
        });

        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHindi.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner));
                btnEnglish.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner_alternate));
                btnHindi.setTextColor(getResources().getColor(R.color.white));
                btnEnglish.setTextColor(getResources().getColor(R.color.pink_high));
            }
        });

        rlProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
