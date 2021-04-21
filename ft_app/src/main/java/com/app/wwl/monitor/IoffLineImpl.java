package com.app.wwl.monitor;

import android.app.Application;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wl.comment.ArouterPath;
import com.wl.service.IOfflineMonitorService;

public class IoffLineImpl {
    public IoffLineImpl() {
        ARouter.getInstance().inject(this);

    }
    @Autowired(name = ArouterPath.offline_monitor_servie)
    IOfflineMonitorService service;

    private Application application;
    public IoffLineImpl setContext(Application a){
        this.application=a;
        return this;
    }


    public IoffLineImpl openStrictMode(){
        if (service!=null)
        service.openStrictMode(application);
        return this;
    }

    public IoffLineImpl beginBlockCanary(){
        if (service!=null){
            service.beginBlockCanary(application);
        }
        return this;
    }
}
