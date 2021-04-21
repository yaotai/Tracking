package com.wl.detect_library.memory;

import android.app.Application;

import com.kwai.koom.javaoom.KOOM;
import com.kwai.koom.javaoom.report.HeapReportUploader;
import com.wl.utls.WLog;

import java.io.File;

/**
 *
 * https://gitee.com/mirrors/KOOM
 * KOOM——高性能线上内存监控方案
 * KOOM(Kwai OOM, Kill OOM)是快手性能优化团队在处理移动端OOM问题的过程中沉淀出的一套完整解决方案。
 *
 * 其中Android Java内存部分在LeakCanary的基础上进行了大量优化，解决了线上内存监控的性能问题，在不影响用户体验的前提下线上采集内存镜像并解析。从 2020 年春节后在快手主APP上线至今解决了大量OOM问题，其性能和稳定性经受住了海量用户与设备的考验，因此决定开源以回馈社区，并欢迎大家来帮助我们改进。
 *
 * KOOM 背景
 * 随着移动端业务逻辑日益复杂，4K编解码、AR魔表等高内存需求场景的逐渐普及，OOM问题已成为快手客户端稳定性治理的头号顽疾。 在日常版本迭代过程中，间或会发生OOM激增，而线上环境非常复杂，仅AB实验就有上千种，事前预防以及事后还原都无法做到，因此急需高性能的线上内存监控方案。一期开源的Android Java内存监控方案，我们调研了LeakCanary以及美团和UC等发表的相关技术文章，发现业内的优化方向主要集中在内存镜像的解析部分，而内存镜像dump部分，一直没有方案能解决dump过程中app长时间冻结的问题。经过深入研究，我们发现可以利用Copy-on-write机制fork子进程dump，满足我们的需求。
 */
public class MemoryMonitorManager {
    private static class H {
        private static MemoryMonitorManager memoryMonitorManager = new MemoryMonitorManager();
    }

    public static MemoryMonitorManager l() {
        //koom线上内存监控

        return H.memoryMonitorManager;
    }



    public void init(Application context){
        KOOM.init(context);
    }
    public void detect() {
        KOOM.getInstance().setHeapReportUploader(new HeapReportUploader() {
            @Override
            public void upload(File file) {
                //发生内存异常生成文件回调
                WLog.print("KOOM file:"+file.getPath());
                //TODO upToSevice Action
                boolean isUpload=false;
                if (isUpload) {
                    file.delete();
                }
            }
        });
    }

    //手动提交内存异常信息文件
    public void getReportManually(){
        File reportDir=new File(KOOM.getInstance().getReportDir());
        if (reportDir!=null){
            for (File file:reportDir.listFiles()) {

            }
        }
    }
}
