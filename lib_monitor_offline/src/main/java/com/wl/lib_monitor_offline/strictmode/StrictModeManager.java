package com.wl.lib_monitor_offline.strictmode;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.wl.itf.IManager;
import com.wl.utls.WLog;

public class StrictModeManager implements IManager {
    private StrictMode.ThreadPolicy.Builder modeBuilder;
    private   StrictMode.VmPolicy.Builder vmPolicyBuilder;
    @Override
    public void start(Context application) {
        if (modeBuilder!=null){
            StrictMode.setThreadPolicy(modeBuilder.build());
        }
        if (vmPolicyBuilder!=null){
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
        WLog.printMonitorOff(this.getClass().getSimpleName(),"start");
    }

    private static class H{
        private static StrictModeManager manager=new StrictModeManager();
    }

    public static StrictModeManager l() {
        return H.manager;
    }


    public StrictModeManager modeDetectAll(){
        checkModeBuilder();
        modeBuilder.detectAll();
        return this;
    }

    public StrictModeManager detectNetwork(){
        checkModeBuilder();
        modeBuilder.detectNetwork();
        return this;
    }

    public StrictModeManager detectDiskReads(){
        checkModeBuilder();
        modeBuilder.detectDiskReads();
        return this;
    }
    public StrictModeManager detectDiskWrites(){
        checkModeBuilder();
        modeBuilder.detectDiskWrites();
        return this;
    }

    public StrictModeManager detectCustomSlowCalls(){
        checkModeBuilder();
        modeBuilder.detectCustomSlowCalls();
        return this;
    }

//    public StrictModeManager detectResourceMismatches(){
//        modeBuilder.detectResourceMismatches();
//        return this;
//    }


    public StrictModeManager vmDetectAll(){
        checkVmBuilder();
        vmPolicyBuilder.detectAll();
        return this;
    }

    public StrictModeManager detectActivityLeaks(){
        checkVmBuilder();
        vmPolicyBuilder.detectActivityLeaks();
        return this;
    }


    public StrictModeManager detectLeakedSqlLiteObjects(){
        checkVmBuilder();
        vmPolicyBuilder.detectLeakedSqlLiteObjects();
        return this;
    }


    public StrictModeManager detectLeakedClosableObjects(){
        checkVmBuilder();
        vmPolicyBuilder.detectLeakedClosableObjects();
        return this;
    }


    public StrictModeManager detectFileUriExposure(){
        checkVmBuilder();
        vmPolicyBuilder.detectFileUriExposure();
        return this;
    }



    public void checkModeBuilder(){
        if (modeBuilder==null)
        modeBuilder=new StrictMode.ThreadPolicy.Builder();
    }

    public void checkVmBuilder(){
        if (vmPolicyBuilder==null)
        vmPolicyBuilder=new StrictMode.VmPolicy.Builder();
    }


}
