package com.app.wwl.hook;

import com.wl.utls.LogIns;
import com.wl.utls.WLog;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;

public class LogHookImpl {
    public static void hook(){
        DexposedBridge.hookAllConstructors(LogIns.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
              //  DexposedBridge.findAndHookMethod(LogIns.class,"print",String.class,new LogHook());
                DexposedBridge.findAndHookMethod(LogIns.class,"print",String.class,String.class,new LogHook());

            }
        });
    }
}
