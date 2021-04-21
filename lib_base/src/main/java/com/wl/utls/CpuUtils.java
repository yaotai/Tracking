package com.wl.utls;

public class CpuUtils {
    private static final int CPU_COUNT=Runtime.getRuntime().availableProcessors();
    //获取cpu核数,仿asyncTask源码写法
    public static int cpuCount(){
        return Math.max(2,Math.min(CPU_COUNT-1,4));
    }
}
