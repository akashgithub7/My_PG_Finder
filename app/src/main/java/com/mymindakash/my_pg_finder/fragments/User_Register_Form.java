package com.mymindakash.my_pg_finder.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.mymindakash.my_pg_finder.R;


/**
 * Created by Vikram on 12/3/2016.
 */

public class User_Register_Form extends Fragment {

    private EditText etName,etNumber,etPassword,etEmail;
    private Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.user_register_form,container,false);
        etName= (EditText) view.findViewById(R.id.etName);
        etNumber= (EditText) view.findViewById(R.id.etNumber);
        etEmail= (EditText) view.findViewById(R.id.etEmail);
        etPassword= (EditText) view.findViewById(R.id.etPassword);
        btnRegister= (Button) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equals("")){
                    etName.setError("error");
                }else if(etNumber.getText().toString().equals("")){
                    etNumber.setError("error");
                }else  if(etEmail.getText().toString().equals("")){
                    etEmail.setError("error");
                }else  if(etPassword.getText().toString().equals("")){
                    etPassword.setError("error");
                }else {
                    etName.setText("");
                    etNumber.setText("");
                    etEmail.setText("");
                    etPassword.setText("");

                }
            }
        });
        return view;
    }

}
