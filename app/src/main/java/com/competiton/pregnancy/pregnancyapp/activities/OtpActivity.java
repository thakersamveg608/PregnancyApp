package com.competiton.pregnancy.pregnancyapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.competiton.pregnancy.pregnancyapp.R;

public class OtpActivity extends AppCompatActivity {

    private EditText etOtp;
    private TextView tvSubmit;
    private ImageView logo;
    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initialize();
        Glide.with(this).load(R.drawable.pregacarelogo1).into(logo);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(OtpActivity.this);
                otp = etOtp.getText().toString().trim();
                if (otp.isEmpty()) {
                    Toast.makeText(OtpActivity.this, getString(R.string.err_field_empty), Toast.LENGTH_LONG).show();
                } else if (otp.equals("1234")) {
                    Toast.makeText(OtpActivity.this, "Otp successfully verified", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(OtpActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(OtpActivity.this, "Wrong Otp", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initialize() {
        etOtp = findViewById(R.id.et_otp);
        logo = findViewById(R.id.logo_otp);
        tvSubmit = findViewById(R.id.tv_submit_otp);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
