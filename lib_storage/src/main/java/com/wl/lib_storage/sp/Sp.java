package com.wl.lib_storage.sp;

import android.content.Context;
import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

public interface Sp {
    void init(Context context);

    void putString(String key, String value);

    String getString(String key);

    void putInt(String key, int value);

    int getInt(String key);

    void putBoolean(String key, boolean value);

    boolean getBoolean(String key);

    void putFloat(String key, float value);

    float getFloat(String key);

    void putDouble(String key, double value);

    double getDouble(String key);

    void putLong(String key, long value);

    long getLong(String value);

    void putBytes(String key, byte[] value);

    byte[] getBytes(String key);

    void putParcelable(String key, Parcelable parcelable);

    Parcelable getParchlable(String key,Class<?extends Parcelable> c);

    void putSet(String key, Set<String> set);

    Set<String> getSet(String key);


}
