package com.wl.lib_log;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Locale;

/**
 * 简单日志工具
 */
public class Logger {
    private static String TAG = "DebugLog";
    private static boolean isDebug = false;

    private static String generateTag() {
        String tag = "%s.%s(Line:%d)";
        try {
            StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
            String callerClazzName = caller.getClassName();
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
            tag = String.format(Locale.US, tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        } catch (Exception e) {
            tag = TAG;
        }
        return TextUtils.isEmpty(TAG) ? tag : TAG + ":" + tag;
    }

    public static void setDebug(boolean isDebug) {
       Logger.isDebug = isDebug;
    }

    public static void setTag(String tag) {
       Logger.TAG = tag;
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.d(generateTag(), String.format(message, args));
        }
    }

    public static void d(@Nullable Object object) {
        if (isDebug) {
            Log.d(generateTag(), toString(object));
        }
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.e(generateTag(), String.format(message, args));
        }
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.e(generateTag(), String.format(message, args), throwable);
        }
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.w(generateTag(), String.format(message, args));
        }
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.i(generateTag(), String.format(message, args));
        }

    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.v(generateTag(), String.format(message, args));
        }
    }

    public static void wtf(@NonNull String message, @Nullable Object... args) {
        if (isDebug) {
            Log.wtf(generateTag(), String.format(message, args));
        }
    }


    public static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (!object.getClass().isArray()) {
            return object.toString();
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        }
        return "Couldn't find a correct type for the object";
    }

}
