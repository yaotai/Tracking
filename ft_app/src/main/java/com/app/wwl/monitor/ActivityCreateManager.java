package com.app.wwl.monitor;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wl.utls.WLog;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.Scope;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

public class ActivityCreateManager {
    @TargetClass(value = "com.wwl.skin.library.SkinActivity", scope = Scope.LEAF)
    @Insert(value = "getContextInfo", mayCreateSuper = true)
    private void getContextInfo(Activity skinActivity) {
        WLog.print("ActivityCreateManager getContextInfo:" + skinActivity.getLocalClassName());
        WLog.print("ActivityCreateManager getContextInfo:" + skinActivity.getComponentName());
        Origin.callVoid();
    }

    @TargetClass(value = "com.wwl.skin.library.SkinActivity", scope = Scope.LEAF)
    @Insert(value = "onCreate", mayCreateSuper = true)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();
        Origin.callVoid();
        WLog.print("ActivityCreateManager onCreate cost:" + (System.currentTimeMillis()-startTime));
    }

    @TargetClass(value = "com.wwl.skin.library.SkinActivity", scope = Scope.LEAF)
    @Insert(value = "onStart", mayCreateSuper = true)
    protected void onStart() {
        long startTime = System.currentTimeMillis();
        Origin.callVoid();
        WLog.print("ActivityCreateManager onStart cost:" + (System.currentTimeMillis()-startTime));
    }

    @TargetClass(value = "com.wwl.skin.library.SkinActivity", scope = Scope.LEAF)
    @Insert(value = "onResume", mayCreateSuper = true)
    protected void onResume() {
        long startTime = System.currentTimeMillis();
        Origin.callVoid();
        WLog.print("ActivityCreateManager onResume cost:" + (System.currentTimeMillis()-startTime));
    }

}
