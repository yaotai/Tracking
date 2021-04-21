package com.wl.utls;

public class LogIns {
    private static class H{
        private static LogIns ins=new LogIns();
    }
    public static LogIns l(){
        return H.ins;
    }
    public void print(String tag,String msg){
        WLog.print(msg);
    }
}
