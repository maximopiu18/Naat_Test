package com.naat.test.provider;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    SharedPreferences sharedpreferences;


    public void setSaveLogin(Context context, String user, String password){
        sharedpreferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("key_user", user);
        editor.putString("key_pass", password);
        editor.commit();
    }
    public String getSaveLoginUser(Context context){
        sharedpreferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String b =sharedpreferences.getString("key_user", "");
        return  b;
    }
    public String getSaveLoginPass(Context context){
        sharedpreferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String b =sharedpreferences.getString("key_pass", "");
        return  b;
    }

}
