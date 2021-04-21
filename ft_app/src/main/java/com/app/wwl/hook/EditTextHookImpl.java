package com.app.wwl.hook;



 import android.widget.EditText;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;

public class EditTextHookImpl {
 public static void hook(){
     DexposedBridge.hookAllConstructors(EditText.class,new XC_MethodHook(){
         @Override
         protected void afterHookedMethod(MethodHookParam param) throws Throwable {
             super.afterHookedMethod(param);
             DexposedBridge.findAndHookMethod(StringHookImpl.class,"getContent",new EditTextHook());
         }
     });
 }
}
