package com.wl.lib_storage.db;

import com.wl.lib_storage.db.auto.UserDao;

public class UserDbManager extends BaseDbManager {
    private static class H {
        private static UserDbManager manager = new UserDbManager();
    }

    public static UserDbManager l() {
        return H.manager;
    }

    public void query(){
        UserDao userDao=daoSession.getUserDao();
        userDao.loadAll();
        //查询年龄大于10得
        userDao.queryRaw("where AGE>?","10");
        userDao.loadByRowId(1);
    }


}
