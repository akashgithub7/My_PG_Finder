package com.mymindakash.my_pg_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Akash117759 on 03-12-2016.
 */

public class Owner_Registration_Activity extends AppCompatActivity {

    EditText etName, etEmailId, etPassword, etConfirmPass, etMobile;
    Button btnSubmit;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";




    public boolean isValidEmail() {
        Pattern ps = Pattern.compile(emailPattern);
        Matcher ms = ps.matcher(etEmailId.getText().toString());
        boolean bs = ms.matches();
        if (bs == false) {
            Toast.makeText(getApplicationContext(),"Email id is incorrect",Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_registration_layout);

        etName=(EditText)findViewById(R.id.etName);
        etEmailId=(EditText)findViewById(R.id.etMailId);
        etMobile=(EditText)findViewById(R.id.etMobileNo);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etConfirmPass=(EditText)findViewById(R.id.etConfirmPass);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(etName.length()==0){
                        etName.setError("Enter Name");
                    }

                    else if(etEmailId.length()==0){
                            etEmailId.setError("Enetr Mail Id");
                    }
                    else if(isValidEmail()==false){
                        etEmailId.setError("Enter Valid Mail id");
                    }
                    else if (etMobile.length()!=10){
                        etMobile.setError("Enter Valid Mobile Number");
                    }
                    else if(etPassword.length()==0){
                        etPassword.setError("Enter Password");
                    }
                    else if (etPassword.length()<6){
                        etPassword.setError("Min Password length should be more than 6 digit");
                    }
                    else if (etConfirmPass.length()==0){
                        etConfirmPass.setError("Enter Confirm Password");
                    }
                    else if(!etPassword.equals(etConfirmPass)){
                        etConfirmPass.setError("Password and Confirm Password did not matched");
                    }
                    else{
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                    }
                }catch (Exception e){

                }
            }
        });
    }
}
