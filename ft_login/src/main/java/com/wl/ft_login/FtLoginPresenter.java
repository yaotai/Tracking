package com.wl.ft_login;

import rx.Observable;
import rx.Observer;

public class FtLoginPresenter implements FtLoginLoginContract.Presenter {
    private FtLoginLoginContract.View view;
    private FtLoginLoginContract.Model model;

    public FtLoginPresenter(FtLoginLoginContract.View view) {
        this.view = view;
        model = new FtLoginModel();
    }

    @Override
    public void getAuthorize(String userName) {
     //   Observable<R> compose = model.getAuthorize(userName).compose(view.<String>bindToLife());
    }
}