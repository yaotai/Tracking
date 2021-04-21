package com.wl.lib_storage.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

/**
 * https://github.com/Tencent/MMKV
 */
public class SpManager implements Sp {
    private static class H{
        private static SpManager manager=new SpManager();
    }
    public static SpManager l(){
        return H.manager;
    }
    @Override
    public void init(Context context){
        String rootDir= MMKV.initialize(context);
        //TODO you can get rootDir
    }
    @Override
    public void putString(String key,String value){
        MMKV.defaultMMKV().encode(key,value);
    }
    @Override
    public String getString(String key){
      return   MMKV.defaultMMKV().decodeString(key);
    }



    @Override
    public void putInt(String key, int value) {
      MMKV.defaultMMKV().encode(key,value);
    }

    @Override
    public int getInt(String key) {
        return MMKV.defaultMMKV().decodeInt(key);
    }

    @Override
    public void putBoolean(String key, boolean value) {
    MMKV.defaultMMKV().encode(key,value);
    }

    @Override
    public boolean getBoolean(String key) {
        return MMKV.defaultMMKV().decodeBool(key);
    }

    @Override
    public void putFloat(String key, float value) {
        MMKV.defaultMMKV().encode(key,value);
    }

    @Override
    public float getFloat(String key) {
        return MMKV.defaultMMKV().decodeFloat(key);
    }

    @Override
    public void putDouble(String key, double value) {
        MMKV.defaultMMKV().encode(key,value);
    }

    @Override
    public double getDouble(String key) {
        return MMKV.defaultMMKV().decodeDouble(key);
    }

    @Override
    public void putLong(String key, long value) {
        MMKV.defaultMMKV().encode(key,value);
    }

    @Override
    public long getLong(String key) {
        return MMKV.defaultMMKV().decodeLong(key);
    }

    @Override
    public void putBytes(String key, byte[] value) {
        MMKV.defaultMMKV().encode(key,value);
    }

    @Override
    public byte[] getBytes(String key) {
        return MMKV.defaultMMKV().decodeBytes(key);
    }

    @Override
    public void putParcelable(String key, Parcelable parcelable) {
        MMKV.defaultMMKV().encode(key,parcelable);
    }

    @Override
    public Parcelable getParchlable(String key,Class<? extends Parcelable> c) {
        return MMKV.defaultMMKV().decodeParcelable(key,c);
    }

    @Override
    public void putSet(String key, Set<String> set) {
        MMKV.defaultMMKV().encode(key,set);
    }

    @Override
    public Set<String> getSet(String key) {
         return MMKV.defaultMMKV().decodeStringSet(key);
    }
}
