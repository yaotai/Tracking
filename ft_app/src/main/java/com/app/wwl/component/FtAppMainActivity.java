package com.app.wwl.component;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.wl.base.mvp.BaseActivity;
//import com.wl.ui.BaseActivity;
import com.wl.lib_keepalive.activitykeep.KeepManager;
import com.wl.lib_keepalive.double_process.LocalService;
import com.wl.lib_keepalive.double_process.RemoteService;


public class FtAppMainActivity extends BaseActivity {
    private ImageView iv_pic;
    private EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ft_app_activity_main);
        KeepManager.getInstance().registScreenBroadcast(this);
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          //  this.startService(new Intent(this, ForegroundService.class));
       // }
        //MyJobService.startJob(this);
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
    }

//    @Override
//    protected int attachLayoutRes() {
//        return ;
//    }
//
//    @Override
//    protected void initInjector() {
//        //        String word= Reflect.on("java.lang.String")
////                .create("Hello,World!")
////                .call("substring",6)
////                .call("toString")
////                .get();
////        System.out.println("joor:"+word);
////        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.big_bg);
////        iv_pic.setImageBitmap(bitmap);
//        WLog.print("registerActivityLifecycleCallbacks:ft_app_activity_main");
//        DataBusManager.l().regist(this, "name", String.class, new DataRecivier<String>() {
//            @Override
//            public void recivceData(String s) {
//                WLog.print("DataBusManager:" + s);
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    // 点击事件
//    // @LoginCheck()
//    public void dayOrNight(View view) {
//        DataBusManager.l().sendData("name", "wahaha");
//        LogIns.l().print("1", "single print");
//        LogIns.l().print("2", "double print");
////        StringHookImpl hook=new StringHookImpl();
////        hook.setContent(et_input.getText().toString());
////        Toast.makeText(this,hook.getContent(),Toast.LENGTH_LONG).show();
//        //   mHandler.sendEmptyMessage(0);
//        NightModeUtils.changeNightMode(this);
//    }
//
//
//    public void secondAty(View view) {
//        startActivity(new Intent(this, SecondActivity.class));
//    }
//
////    @Override
////    protected boolean openChangeSkin() {
////        return true;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unregisScreenReceiver(this);
    }
}
