package com.app.wwl.hook;

import android.graphics.Bitmap;
import android.widget.ImageView;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;

public class ImageHookImpl {
    public static void hook(){
        DexposedBridge.hookAllConstructors(ImageView.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                DexposedBridge.findAndHookMethod(ImageView.class,"setImageBitmap",
                        Bitmap.class,new ImageHook());
            }
        });

    }
}
