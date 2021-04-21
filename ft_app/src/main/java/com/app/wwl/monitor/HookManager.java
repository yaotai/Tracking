package com.app.wwl.monitor;
import com.app.wwl.hook.EditTextHookImpl;
import com.app.wwl.hook.ImageHookImpl;
import com.app.wwl.hook.LogHookImpl;


/**
 * 对所有imageView 的setImageBitmap 方法进行hook
 * 出现bitmap不合理造成内存浪费进行告警
 */
public class HookManager {
    private static class H{
        private static HookManager manager=new HookManager();
    }
    public static HookManager init(){
        return H.manager;
    }

    public void hook(){
        ImageHookImpl.hook();
        EditTextHookImpl.hook();
        LogHookImpl.hook();
    }
}
