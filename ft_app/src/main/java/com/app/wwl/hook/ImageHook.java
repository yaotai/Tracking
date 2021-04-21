package com.app.wwl.hook;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;


import com.wl.utls.WLog;

import de.robv.android.xposed.XC_MethodHook;

public class ImageHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        ImageView imageView=(ImageView)param.thisObject;
        checkBitmap(imageView,((ImageView) param.thisObject).getDrawable());
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);

    }
    private static void checkBitmap(Object thiz, Drawable drawable){

        if(drawable instanceof BitmapDrawable && thiz instanceof View){
            final Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            if(bitmap != null){
                final View view = (View) thiz;
                int width = view.getWidth();
                int height = view.getHeight();
                if(width > 0 && height > 0){
                    //图标宽度都大于view带下的两倍以上，则警告
                    if(bitmap.getWidth() >= (width << 1) && bitmap.getHeight() >= (height << 1)){
                        warn(bitmap.getWidth(),bitmap.getHeight(),width,height);
                    }
                }else {
                    final Throwable stackTrace = new RuntimeException();
                    view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            int w = view.getWidth();
                            int h = view.getHeight();
                            if(w > 0 && h > 0){
                                if(bitmap.getWidth() >= (w << 1) && bitmap.getHeight() >= (h << 1)){
                                    warn(bitmap.getWidth(),bitmap.getHeight(),view.getWidth(),view.getHeight());
                                }
                                view.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                            return true;
                        }
                    });
                }
            }
        }

    }

    private static void warn(int bitmapWidth, int bitmapHeight, int viewWidth, int viewHeight){
        String warnInfo = new StringBuilder("Bitmap size too large: ")
                .append("\n real size: (").append(bitmapWidth).append(")")
                .append("\n desired size: (").append(viewWidth).append(")")
                .append("\n call stack trace : \n").append(Log.getStackTraceString(new Throwable()))
                .toString();
        WLog.print(warnInfo);
    }


}