package com.wl.lib_monitor_offline;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wl.comment.ArouterPath;
import com.wl.lib_monitor_offline.debug_detect_libary.anr.PerformanceManager;
import com.wl.lib_monitor_offline.strictmode.StrictModeManager;
import com.wl.service.IOfflineMonitorService;
@Route(path = ArouterPath.offline_monitor_servie)
public class OfflineMonitorImpl implements IOfflineMonitorService {
    private Context context;
    @Override
    public void init(Context context) {
        this.context=context;
    }

    @Override
    public void openStrictMode(Application application) {
        StrictModeManager.l().modeDetectAll().vmDetectAll().start(application);
     }

    @Override
    public void beginBlockCanary(Application application) {
        PerformanceManager.l().start(application);
     }
}
