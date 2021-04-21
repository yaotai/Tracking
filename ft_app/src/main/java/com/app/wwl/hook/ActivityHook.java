package com.app.wwl.hook;

import com.wl.utls.WLog;

import de.robv.android.xposed.XC_MethodHook;

public class ActivityHook extends XC_MethodHook {
    private long startTime=0;
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        startTime=System.currentTimeMillis();
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        long costTime=System.currentTimeMillis()-startTime;
        WLog.print("setContentView 花费时间:"+costTime);
    }
}
