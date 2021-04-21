package com.app.wwl.monitor;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.os.Build;

public class NetWrokDetectManager {
    private static class H{
        private static NetWrokDetectManager manager=new NetWrokDetectManager();
    }
    public static NetWrokDetectManager init(){
        return H.manager;
    }
    public void begin(Context c){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        NetworkStatsManager manager=
                (NetworkStatsManager)c.getSystemService( (Context.NETWORK_STATS_SERVICE));
    }
}
