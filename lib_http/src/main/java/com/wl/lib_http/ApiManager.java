package com.wl.lib_http;

import com.wl.lib_http.converter.FastJsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiManager {
    private static class H{
        private static ApiManager manager=new ApiManager();
    }
    public static ApiManager l(){
        return H.manager;
    }
    /**
     * 构建httpclient
     * @return
     */
    private OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //设置断线重连
                .retryOnConnectionFailure(true)
                //设置超时
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        return builder.build();
    }
    private Retrofit.Builder buildRetrofit(OkHttpClient okHttpClient,String host){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(host)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为json的支持(以实体类返回)
                .addConverterFactory(FastJsonConverterFactory.create());
                //增加返回值为Oservable<T>的支持
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public <T> T createApiService(String host,Class<T> serviceClass){
        if (host==null){
            return buildRetrofit(buildOkHttpClient(),ApiConstance.Host).build().create(serviceClass);
        }
        return buildRetrofit(buildOkHttpClient(),host).build().create(serviceClass);
    }
}
