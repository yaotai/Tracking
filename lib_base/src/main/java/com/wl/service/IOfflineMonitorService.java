package com.wl.service;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IOfflineMonitorService extends IProvider {
    void openStrictMode(Application application);
    void beginBlockCanary(Application application);
}
