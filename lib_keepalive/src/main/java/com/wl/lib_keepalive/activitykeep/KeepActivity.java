package com.wl.lib_keepalive.activitykeep;

import android.os.Bundle;
import android.view.Gravity;

import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
/**
 * 通过监听手机锁屏广播，在屏幕锁屏时启动1个像素透明的 Activity，然后在用户解锁时将 Activity 销毁掉**，使得原本处于后台进程的
 * 变成前台进程，从而达到提高进程优先级的作用。
 */
public class KeepActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=getWindow();
        window.setGravity(Gravity.START|Gravity.TOP);//设置Activity 显示在左上角
        WindowManager.LayoutParams attrs=window.getAttributes();
        attrs.width=1;
        attrs.height=1;
        attrs.x=0;
        attrs.y=0;
        attrs.alpha=1;
        window.setAttributes(attrs);
        KeepManager.getInstance().setGuardActivity(this);
    }


}
