package com.wl.lib_monitor_offline.debug_detect_libary.anr;

import android.app.Application;
import android.content.Context;

import androidx.annotation.UiThread;

import com.github.moduth.blockcanary.BlockCanary;
import com.wl.itf.IManager;
import com.wl.utls.WLog;

public class PerformanceManager implements IManager {
    /**
     * https://github.com/markzhai/AndroidPerformanceMonitor
     * 非侵入式的性能监控组件,通知形式弹出卡顿信息
     * compile 'com.github.markzhai:blockcanary-android:1.5.0'
     */
    private static class H {
        private static PerformanceManager manager = new PerformanceManager();
    }
    public static PerformanceManager l(){
        return H.manager;
    }

    @Override
    public void start(Context context) {
        WLog.printMonitorOff(this.getClass().getSimpleName(),"start");
        BlockCanary.install(context, new AppBlockCanaryContext()).start();
    }
}
