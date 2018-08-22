package com.competiton.pregnancy.pregnancyapp.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.competiton.pregnancy.pregnancyapp.R;
import com.competiton.pregnancy.pregnancyapp.database.DatabaseHelperUser;
import com.competiton.pregnancy.pregnancyapp.database.UserDb;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputSignAadhaar, inputSignMobile, inputSignPassword, inputSignRetypePassword;
    private TextInputLayout inputLayoutSignAadhar, inputLayoutSignMobile, inputLayoutSignPassword, inputLayoutSignRetypePassword;
    private Button btnRegister;
    private String aadhaar,mobile,password,retypePassword;
    DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intialize();
        inputSignAadhaar.addTextChangedListener(new MyTextWatcher(inputSignAadhaar));
        inputSignMobile.addTextChangedListener(new MyTextWatcher(inputSignMobile));
        inputSignPassword.addTextChangedListener(new MyTextWatcher(inputSignPassword));
        inputSignRetypePassword.addTextChangedListener(new MyTextWatcher(inputSignRetypePassword));

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void intialize() {
        inputLayoutSignAadhar = (TextInputLayout) findViewById(R.id.input_layout_aadhar);
        inputLayoutSignMobile = (TextInputLayout) findViewById(R.id.input_layout_register_mobile);
        inputLayoutSignPassword = (TextInputLayout) findViewById(R.id.input_layout_register_password);
        inputLayoutSignRetypePassword = (TextInputLayout) findViewById(R.id.input_layout_confirm_password);
        inputSignAadhaar = (EditText) findViewById(R.id.input_aadhar);
        inputSignMobile = (EditText) findViewById(R.id.input_register_mobile);
        inputSignPassword = (EditText) findViewById(R.id.input_register_password);
        inputSignRetypePassword = (EditText) findViewById(R.id.input_confirm_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }


    public void submit(){
        if (!validateAadhaar()){
            return;
        }
        if (!validateMobile()){
            return;
        }
        if (!validatePassword()){
            return;
        }
        if (!validateRetypePassword()){
            return;
        }

        UserDb userDb = new UserDb();
        userDb.setAadhaarId(aadhaar);
        userDb.setMobile(mobile);
        userDb.setPassword(password);

        databaseHelperUser.insertUser(userDb);
        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    private boolean validateAadhaar(){
        aadhaar = inputSignAadhaar.getText().toString().trim();
        if (aadhaar.isEmpty()){
            inputLayoutSignAadhar.setError(getString(R.string.err_field_empty));
            requestFocus(inputSignAadhaar);
            return false;
        }else {
            inputLayoutSignAadhar.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateMobile(){
        mobile = inputSignMobile.getText().toString().trim();
        if (mobile.isEmpty()){
            inputLayoutSignMobile.setError(getString(R.string.err_field_empty));
            requestFocus(inputSignMobile);
            return false;
        }else if (mobile.length()!=10){
            inputLayoutSignMobile.setError("Mobile Number must contain 10 digits");
            requestFocus(inputSignMobile);
            return false;
        }else {
            inputLayoutSignMobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword(){
        password = inputSignPassword.getText().toString().trim();
        if (password.isEmpty()){
            inputLayoutSignPassword.setError(getString(R.string.err_field_empty));
            requestFocus(inputSignPassword);
            return false;
        }else {
            inputLayoutSignPassword.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateRetypePassword(){
        retypePassword = inputSignRetypePassword.getText().toString().trim();
        if (retypePassword.isEmpty()){
            inputLayoutSignRetypePassword.setError(getString(R.string.err_field_empty));
            requestFocus(inputSignRetypePassword);
            return false;
        }else if(!password.equals(retypePassword)){
            inputLayoutSignRetypePassword.setError("This does not match with the password");
            requestFocus(inputSignRetypePassword);
            return false;
        }
        else {
            inputLayoutSignPassword.setErrorEnabled(false);
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
                case R.id.input_aadhar:
                    validateAadhaar();
                    break;
                case R.id.input_register_mobile:
                    validateMobile();
                    break;
                case R.id.input_register_password:
                    validatePassword();
                    break;
                case R.id.input_confirm_password:
                    validateRetypePassword();
                    break;
            }
        }
    }

}
