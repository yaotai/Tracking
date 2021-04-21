package com.wl.lib_storage.db;

import android.content.Context;

import com.wl.lib_storage.db.auto.DaoMaster;
import com.wl.lib_storage.db.auto.DaoSession;

public class BaseDbManager {
    private static final String db_name = "WinApp-db";
    private DaoMaster.DevOpenHelper devOpenHelper;
    protected DaoSession daoSession;

    public BaseDbManager init(Context context) {
        if (devOpenHelper == null || daoSession == null) {
            devOpenHelper = new DaoMaster.DevOpenHelper(context, db_name, null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return this;
    }
}
