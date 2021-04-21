package com.wl.debug_detect_libary.anr;

import android.app.Application;

import androidx.annotation.UiThread;

import com.github.moduth.blockcanary.BlockCanary;

public class PerformanceManager {
    /**
     * https://github.com/markzhai/AndroidPerformanceMonitor
     *非侵入式的性能监控组件,通知形式弹出卡顿信息
     *  compile 'com.github.markzhai:blockcanary-android:1.5.0'
     */
        private static class H{
            private static PerformanceManager manager=new PerformanceManager();
        }
        public static PerformanceManager getInstance(){
            return H.manager;
        }
        //application 初始化
        @UiThread
        public void start(Application application){
            BlockCanary.install(application,new AppBlockCanaryContext()).start();
        }

}
