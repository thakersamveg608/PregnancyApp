package com.competiton.pregnancy.pregnancyapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.database.DatabaseHelperUser;
import com.competiton.pregnancy.pregnancyapp.utils.SharedPrefs;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class LoginActivity extends AppCompatActivity {

    private EditText inputLoginMobile, inputLoginPassword;
    private TextInputLayout inputLayoutLoginMobile, inputLayoutLoginPassword;
    private String mobile, password, valPassword;
    private Button btnLogin;
    private TextView newUser;
    private ImageView logo;
    private SharedPrefs sharedPrefs;
    DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        sharedPrefs = new SharedPrefs(this);
        inputLoginMobile.addTextChangedListener(new MyTextWatcher(inputLoginMobile));
        inputLoginPassword.addTextChangedListener(new MyTextWatcher(inputLoginPassword));
        Glide.with(this).load(R.drawable.pregacarelogo1).into(logo);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(LoginActivity.this);
                submit();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void initialize() {
        inputLayoutLoginMobile = (TextInputLayout) findViewById(R.id.input_layout_login_mobile);
        inputLayoutLoginPassword = (TextInputLayout) findViewById(R.id.input_layout_login_password);
        inputLoginMobile = (EditText) findViewById(R.id.input_login_mobile);
        inputLoginPassword = (EditText) findViewById(R.id.input_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        newUser = (TextView) findViewById(R.id.new_user);
        logo = findViewById(R.id.logo_login);
    }

    public void submit(){
        if (!validateMobile()){
            return;
        }
        if (!validatePassword()){
            return;
        }
        valPassword = databaseHelperUser.searchPass(mobile);
        if (password.equals(valPassword)){
            sharedPrefs.setLogin(true);
            Toast.makeText(this,"Login Successfull",Toast.LENGTH_LONG).show();
            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(this,"Credentials doest not match",Toast.LENGTH_LONG).show();
        }
    }


    private boolean validateMobile(){
        mobile = inputLoginMobile.getText().toString().trim();
        if (mobile.isEmpty()){
            inputLayoutLoginMobile.setError(getString(R.string.err_field_empty));
            requestFocus(inputLoginMobile);
            return false;
        }else if (mobile.length()!=10){
            inputLayoutLoginMobile.setError("Mobile Number must contain 10 digits");
            requestFocus(inputLoginMobile);
            return false;
        }else {
            inputLayoutLoginMobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword(){
        password = inputLoginPassword.getText().toString().trim();
        if (password.isEmpty()){
            inputLayoutLoginPassword.setError(getString(R.string.err_field_empty));
            requestFocus(inputLoginPassword);
            return false;
        }else {
            inputLayoutLoginPassword.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_login_mobile:
                    validateMobile();
                    break;
                case R.id.input_login_password:
                    validatePassword();
                    break;
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
