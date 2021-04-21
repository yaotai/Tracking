package com.wl.strictmode;

import android.os.StrictMode;

public class StrictModeManager {
    private static class H{
        private static StrictModeManager manager=new StrictModeManager();
    }
    public static StrictModeManager init(){
        return H.manager;
    }

    public StrictModeManager beginThreadPolicy(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll() 
                .build());
        return this;
    }

    public StrictModeManager beginVmPolicy(){
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectAll().build());
        return this;
    }
}
