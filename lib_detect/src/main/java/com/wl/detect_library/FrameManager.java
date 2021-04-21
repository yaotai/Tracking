package com.wl.detect_library;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Looper;
import android.view.Choreographer;

public class FrameManager {
    /**
     * 获取手机刷新频率 FPS
     * mFrameCount 是超过16ms渲染的次数
     */
    private static class H{
        private static FrameManager manager=new FrameManager();
    }

    public static FrameManager getInstance(){
        return H.manager;
    }
    private long mStartFrameTime = 0;
    private int mFrameCount = 0;
    private static final long MONITOR_INTERVAL = 160L; //单次计算FPS使用160毫秒
    private static final long MONITOR_INTERVAL_NANOS = MONITOR_INTERVAL * 1000L * 1000L;
    private static final long MAX_INTERVAL = 1000L; //设置计算fps的单位时间间隔1000ms,即fps/s;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void getFPS(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                if (mStartFrameTime == 0) {
                    mStartFrameTime = frameTimeNanos;
                }
                long interval = frameTimeNanos - mStartFrameTime;
                if (interval > MONITOR_INTERVAL_NANOS) {
                    double fps = (((double) (mFrameCount * 1000L * 1000L)) / interval) * MAX_INTERVAL;
                   // WLog.print("fps:"+fps);
                    mFrameCount = 0;
                    mStartFrameTime = 0;
                } else {
                    ++mFrameCount;
                    //WLog.print("fps:"+" mFrameCount:"+mFrameCount);
                    Thread mainThread = Looper.getMainLooper().getThread();
                   // WLog.print("fps: Trace-"+mainThread.getStackTrace());

                }
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }
}
