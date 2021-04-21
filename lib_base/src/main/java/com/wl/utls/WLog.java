package com.wl.utls;

import android.util.Log;

public class WLog {
    public static void printMonitorOff(String tag,String msg){
        print(tag,msg);
    }

    public static void print(String tag,String msg){
        Log.i("WWL offline monitor:"+tag,msg);
    }

    public static void print(String msg){
        print("WWL:",msg);
    }
}
