package com.wl.lib_databus;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class DataBusManager {
    private static class H{
        private static DataBusManager manager=new DataBusManager();
    }
    public static DataBusManager l(){
        return H.manager;
    }
    public <T>void regist(@NonNull LifecycleOwner owner,String key, Class  tClass, DataRecivier<T> recivier){
        LiveDataBus.get().with(key).observe(owner, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                recivier.recivceData((T)o);
            }
        });
    }

    public void sendData(String key,String value){
        LiveDataBus.get().with(key).setValue(value);
    }
}
