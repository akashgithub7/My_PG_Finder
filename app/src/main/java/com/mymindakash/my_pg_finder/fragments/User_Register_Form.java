package com.mymindakash.my_pg_finder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mymindakash.my_pg_finder.R;
import com.mymindakash.my_pg_finder.adapter.DataTables;
import com.mymindakash.my_pg_finder.mailsend.SendMail;


/**
 * Created by Vikram on 12/3/2016.
 */

public class User_Register_Form extends Fragment {
    private EditText etName,etNumber,etPassword,etEmail;
    private Button btnRegister;
    Context thisContext;
    SendMail mail;
    DataTables sql;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.user_register_form,container,false);
        etName= (EditText) view.findViewById(R.id.etName);
        etNumber= (EditText) view.findViewById(R.id.etNumber);
        etEmail= (EditText) view.findViewById(R.id.etEmail);
        etPassword= (EditText) view.findViewById(R.id.etPassword);
        btnRegister= (Button) view.findViewById(R.id.btnRegister);
        thisContext=container.getContext();
        sql=new DataTables(thisContext);
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
                    String email = etEmail.getText().toString().trim();
                    mail=new SendMail(thisContext,email,"Welcome","Welcome To My App ");
                    //Executing sendmail to send email
                    mail.execute();
                    insertData();

                }
            }
        });
        return view;
    }
    public void insertData(){
        String name=etName.getText().toString();
        String number=etNumber.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        sql.insertData(name,number,email,password);
       doClear();
    }
    public void doClear(){
        etName.getText().clear();
        etNumber.getText().clear();
        etEmail.getText().clear();
        etPassword.getText().clear();
    }

}
