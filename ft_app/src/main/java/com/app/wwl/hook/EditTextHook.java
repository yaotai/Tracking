package com.app.wwl.hook;



import com.wl.utls.WLog;

import de.robv.android.xposed.XC_MethodHook;

public class EditTextHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
//        EditText editText=(EditText)param.thisObject;
//        Editable editable=(Editable) param.getResult();
       // WLog.print("afterHookedMethod:"+editable.toString());
        StringHookImpl stringHook=(StringHookImpl)param.thisObject;
        String str=((StringHookImpl) param.thisObject).getContent();
        WLog.print("afterHookedMethod 1:"+str);
        str.replace("1","A");
        str.replace("2","B");
        WLog.print("afterHookedMethod 2:"+str);
        param.setResult(str);
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
    }
}
