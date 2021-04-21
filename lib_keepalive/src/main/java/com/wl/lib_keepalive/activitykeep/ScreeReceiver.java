package com.wl.lib_keepalive.activitykeep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class ScreeReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(TextUtils.equals(action,Intent.ACTION_SCREEN_OFF)){
            Log.e("cmo","手机屏幕息屏");
            KeepManager.getInstance().startGuradActivity(context);
        }else if(TextUtils.equals(action,Intent.ACTION_SCREEN_ON)){
            Log.e("cmo","手机屏幕开屏");
            KeepManager.getInstance().finishGuradActivity(context);
        }
    }

}
