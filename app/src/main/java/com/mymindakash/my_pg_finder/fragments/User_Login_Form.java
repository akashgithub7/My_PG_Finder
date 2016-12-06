package com.mymindakash.my_pg_finder.fragments;

import android.content.Context;
import android.database.Cursor;
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
    private EditText etLoginEmail, etLoginPassword;
    private Button btnLogin;
    private List<Contect_List> list;
    private String email, Email;
    private String password, Password;
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
        etLoginPassword = (EditText) view.findViewById(R.id.etLoginPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        sql = new DataTables(thisContext);
        list = new ArrayList<>();
        Email = etLoginEmail.getText().toString();
        Password = etLoginPassword.getText().toString();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etLoginEmail.getText().toString().equals("")) {
                    etLoginEmail.setError("error");
                } else if (etLoginPassword.getText().toString().equals("")) {
                    etLoginPassword.setError("error");
                } else {
                    Cursor cursor = sql.Login(etLoginEmail.getText().toString(), etLoginPassword.getText().toString());
                    while (cursor.moveToNext()) {
                        email = cursor.getString(3);
                        password = cursor.getString(4);

                    }
                    try {

                        if (email.equals(etLoginEmail.getText().toString()) && password.equals(etLoginPassword.getText().toString())) {

                            Toast.makeText(thisContext, "" + email + " " + password, Toast.LENGTH_SHORT).show();
                            doClear();
                            }
                        else if(email.equals(etLoginEmail.getText().toString()) && !password.equals(etLoginPassword.getText().toString())) {
                                Toast.makeText(thisContext, "Plz Enter Valid Password", Toast.LENGTH_SHORT).show();
                            }
                        else if(!email.equals(etLoginEmail.getText().toString()) && password.equals(etLoginPassword.getText().toString())) {
                            Toast.makeText(thisContext, "Plz Enter Valid Email", Toast.LENGTH_SHORT).show();
                        }
                         else {
                            doClear();
                            Toast.makeText(thisContext, "Plz Register First", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e) {
                        doClear();
                        Toast.makeText(thisContext, "Plz Register First", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    public void doClear(){
        etLoginEmail.setText("");
        etLoginPassword.setText("");
    }

}
