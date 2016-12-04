package com.mymindakash.my_pg_finder.fragments;

import android.content.Context;
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
import com.mymindakash.my_pg_finder.adapter.Contect_List;
import com.mymindakash.my_pg_finder.adapter.DataTables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vikram on 12/3/2016.
 */

public class User_Login_Form extends Fragment {
    private EditText etLoginEmail, getEtLoginPassword;
    private Button btnLogin;
    private List<Contect_List> list;
    private String[] email;
    private String[] password;
    Context thisContext;
    DataTables sql;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_login_form, container, false);
        thisContext = container.getContext();
        etLoginEmail = (EditText) view.findViewById(R.id.etLoginEmail);
        getEtLoginPassword = (EditText) view.findViewById(R.id.etLoginPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        sql = new DataTables(thisContext);
        list = new ArrayList<>();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etLoginEmail.getText().toString().equals("")) {
                    etLoginEmail.setError("error");
                } else if (getEtLoginPassword.getText().toString().equals("")) {
                    getEtLoginPassword.setError("error");
                } else {
                    list = sql.showData();
                    email=new String[list.size()];
                    password=new String[list.size()];
                    int i=0;
                    for (Contect_List con : list) {
                        email[i]=con.getEmail();
                        password[i]=con.getPassword();
                        i++;
                    }
                    doRegister();
                }
            }
        });
        return view;
    }

    public void doRegister() {
        String userEmail = etLoginEmail.getText().toString();
        String userPassword = getEtLoginPassword.getText().toString();
        for (int i = 0; i < list.size(); i++) {
            if (userEmail.equals(email[i]) && userPassword.equals(password[i])) {
                Toast.makeText(thisContext, "Login Successfully", Toast.LENGTH_SHORT).show();
                doClear();
                break;
            } else {
                Toast.makeText(thisContext, "Plz Enter Valid Email or Password ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void doClear() {

        etLoginEmail.getText().clear();
        getEtLoginPassword.getText().clear();
    }

}
