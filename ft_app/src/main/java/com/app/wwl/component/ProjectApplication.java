package com.app.wwl.component;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.wwl.monitor.HookManager;
import com.app.wwl.monitor.IoffLineImpl;
import com.wl.utls.WLog;


public class ProjectApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        //bitmap hook
        HookManager.init().hook();
        //内存监控
        //rManager.startWatchRNA(true);

        //开启线下监控
        new IoffLineImpl().setContext(this).beginBlockCanary();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                WLog.print("registerActivityLifecycleCallbacks onActivityCreated:"+activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                WLog.print("registerActivityLifecycleCallbacks onActivityStarted:"+activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                WLog.print("registerActivityLifecycleCallbacks onActivityResumed:"+activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                WLog.print("registerActivityLifecycleCallbacks onActivityPaused:"+activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                WLog.print("registerActivityLifecycleCallbacks onActivityStopped:"+activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                WLog.print("registerActivityLifecycleCallbacks onActivitySaveInstanceState:"+activity.getLocalClassName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                WLog.print("registerActivityLifecycleCallbacks onActivityDestroyed:"+activity.getLocalClassName());
            }
        });
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


}
