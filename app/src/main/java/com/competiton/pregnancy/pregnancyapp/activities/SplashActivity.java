package com.competiton.pregnancy.pregnancyapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.utils.SharedPrefs;

public class SplashActivity extends AppCompatActivity {

    private Button btnEnglish, btnHindi;
    private RelativeLayout rlProceed;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPrefs = new SharedPrefs(this);

        btnEnglish = findViewById(R.id.btnEnglish);
        btnHindi = findViewById(R.id.btnHindi);
        rlProceed = findViewById(R.id.rlProceed);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHindi.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner));
                btnEnglish.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner_alternate));
                btnHindi.setTextColor(getResources().getColor(R.color.white));
                btnEnglish.setTextColor(getResources().getColor(R.color.pink_high));

            }
        });

        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHindi.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner_alternate));
                btnEnglish.setBackground(getResources().getDrawable(R.drawable.button_rounded_corner));
                btnHindi.setTextColor(getResources().getColor(R.color.pink_high));
                btnEnglish.setTextColor(getResources().getColor(R.color.white));
            }
        });

        rlProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefs.setLanguage(true);
                if (!sharedPrefs.isLoggedIn()){
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
