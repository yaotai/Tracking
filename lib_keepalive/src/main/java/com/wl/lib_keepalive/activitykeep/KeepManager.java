package com.wl.lib_keepalive.activitykeep;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepManager {
    private ScreeReceiver mScreeReceiver;
    private static final KeepManager INSTANCE=new KeepManager();
    private WeakReference<Activity> mGuardAct;//弱引用保存，防止Activity内存泄漏

    private KeepManager(){}

    public static KeepManager getInstance(){
        return  INSTANCE;
    }

    /**
     * 注册息屏开屏广播
     * @param context
     */
    public void registScreenBroadcast(Context context){
        if(context!=null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_SCREEN_ON);
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
            mScreeReceiver = new ScreeReceiver();
            context.registerReceiver(mScreeReceiver,intentFilter);
        }
    }

    /**
     * 取消注册广播接收者
     * @param context
     */
    public void unregisScreenReceiver(Context context){
        if(context!=null){
            context.unregisterReceiver(mScreeReceiver);
        }
    }

    /**
     *
     * @param context
     */
    public void startGuradActivity(Context context){
        if(context!=null) {
            Intent intent = new Intent(context, KeepActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     *
     * @param context
     */
    public void finishGuradActivity(Context context){
        if(context!=null){
            if (null != mGuardAct) {
                Activity activity = mGuardAct.get();
                if (null != activity) {
                    activity.finish();
                }
                mGuardAct = null;
            }
        }
    }

    public void setGuardActivity(KeepActivity keep) {
        mGuardAct = new WeakReference<Activity>(keep);
    }


}
