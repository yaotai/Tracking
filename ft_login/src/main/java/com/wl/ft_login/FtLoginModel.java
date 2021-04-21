package com.wl.ft_login;



import com.wl.lib_http.ApiManager;

import retrofit2.Call;
import rx.Observable;

public class FtLoginModel implements FtLoginLoginContract.Model{
    @Override
    public Observable<String> getAuthorize(String userName) {
       // Call<String> call= ApiManager.l().createApiService(null,ILoginService.class).getAuthorize(client_id,response_type,redirect_uri);
        return Observable.just(userName);
    }
}
