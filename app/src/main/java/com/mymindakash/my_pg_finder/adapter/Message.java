package com.mymindakash.my_pg_finder.adapter;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Vikram on 12/4/2016.
 */

public class Message {
    public static  void message(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
