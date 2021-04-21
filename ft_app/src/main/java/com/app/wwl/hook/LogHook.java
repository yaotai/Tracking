package com.app.wwl.hook;

import android.util.Log;

import com.wl.utls.WLog;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;

public class LogHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
       // Object [] arr= param.args;

    }
}
