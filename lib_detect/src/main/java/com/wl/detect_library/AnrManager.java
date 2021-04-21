package com.wl.detect_library;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.wl.utls.WLog;

public class AnrManager extends Thread {
    public static void startWatchRNA(boolean isStart) {
        if (isStart) {
            new AnrManager().start();
        }
    }

    public static final int MESSAGE_WATCHDOG_TIME_TICK = 0;
    /**
     * 判定Activity发生了ANR的时间，必须要小于5秒，否则等弹出ANR，可能就被用户立即杀死了。
     */
    public static final int ACTIVITY_ANR_TIMEOUT = 2000;


    private static int lastTimeTick = -1;
    private static int timeTick = 0;


    private Handler watchDogHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            timeTick++;
            timeTick = timeTick % Integer.MAX_VALUE;
        }
    };

    @Override
    public void run() {
        while (true) {
            watchDogHandler.sendEmptyMessage(MESSAGE_WATCHDOG_TIME_TICK);
            try {
                Thread.sleep(ACTIVITY_ANR_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果相等，说明过了ACTIVITY_ANR_TIMEOUT的时间后AnrManager仍没有处理消息，已经ANR了
            if (timeTick == lastTimeTick) {
                //throw new ANRException();
                Thread mainThread = Looper.getMainLooper().getThread();
                WLog.print("-----------应用程序无响应begin----------");
                WLog.print(explain(mainThread.getStackTrace()));
                WLog.print("-----------应用程序无响应end-------------");
            } else {
                lastTimeTick = timeTick;
            }
        }
    }

//    public class ANRException extends RuntimeException {
//        public ANRException() {
//            super("-----------应用程序无响应，ANR定位-----------");
//            Thread mainThread = Looper.getMainLooper().getThread();
//            setStackTrace(mainThread.getStackTrace());
//        }
//    }

    private String explain(StackTraceElement[] stackTrace) {
        if (stackTrace != null && stackTrace.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (StackTraceElement stack : stackTrace) {
                builder.append("---------------------\n");
                builder.append(stack.getClassName() + "\n");
                builder.append(stack.getFileName() + "\n");
                builder.append(stack.getMethodName() + "\n");
                builder.append(stack.getLineNumber() + "\n");
                builder.append(stack.toString() + "\n");
                builder.append("---------------------\n");
            }
            return builder.toString();
        }
        return "StackTrace is Empty";
    }
}
