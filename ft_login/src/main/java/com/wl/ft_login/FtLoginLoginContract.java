package com.wl.ft_login;

import com.wl.base.mvp.BaseModel;
import com.wl.base.mvp.BasePresenter;
import com.wl.base.mvp.BaseView;

import rx.Observable;

public class FtLoginLoginContract {
    interface Model extends BaseModel{
    Observable<String> getAuthorize(String userName);
    }

    interface Presenter extends BasePresenter{
        void getAuthorize(String userName);
    }
    interface View extends BaseView{
        void toHome();
    }
}
