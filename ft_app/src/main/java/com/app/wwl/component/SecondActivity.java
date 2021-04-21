package com.app.wwl.component;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.wwl.skin.library.SkinActivity;
import com.wwl.skin.library.utils.PreferencesUtils;


public class SecondActivity extends SkinActivity {
//private String [] arr=new String[10000];
    Looper l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        boolean isNight = PreferencesUtils.getBoolean(this, "isNight");
        if (isNight) {
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
//        handler.sendEmptyMessageDelayed(1,2000);
//        finish();
    }
    @Override
    protected boolean openChangeSkin() {
        return true;
    }
    private void taskTest(){

    }

    private  Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        //    arr[99]="99";
        }
    };
}