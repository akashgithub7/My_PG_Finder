package com.mymindakash.my_pg_finder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mymindakash.my_pg_finder.R;

/**
 * Created by Vikram on 12/3/2016.
 */

public class User_Login_Form extends Fragment {
    private EditText etLoginEmail,getEtLoginPassword;
    private Button btnLogin;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.user_login_form,container,false);
        etLoginEmail= (EditText) view.findViewById(R.id.etLoginEmail);
        getEtLoginPassword= (EditText) view.findViewById(R.id.etLoginPassword);
        btnLogin= (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(etLoginEmail.getText().toString().equals("")  ){
                  etLoginEmail.setError("error");
              }else if ( getEtLoginPassword.getText().toString().equals("")){
                  getEtLoginPassword.setError("error");
              }else {
                  etLoginEmail.setText("");
                  getEtLoginPassword.setText("");
              }
            }
        });
        return view;
    }


}
