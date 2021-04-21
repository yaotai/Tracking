package com.wl.ft_login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILoginService {
    @GET("/action/oauth2/authorize")
    Call<String> getAuthorize(@Query("client_id") String client_id,@Query("response_type") String response_type,@Query("redirect_uri") String redirect_uri);
}
