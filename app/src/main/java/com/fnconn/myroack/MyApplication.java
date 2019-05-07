package com.fnconn.myroack;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.fnconn.roach.Cockroach;
import com.fnconn.roach.ExceptionHandler;

/**
 * ClassName: MyApplication.java
 * Description:
 * Author: fpm0300
 * Date: 2019/5/7
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        install();
    }

    private void install() {

        final Thread.UncaughtExceptionHandler sysExcepHandler = Thread.getDefaultUncaughtExceptionHandler();
        Cockroach.install(this, new ExceptionHandler() {
            @Override
            protected void onUncaughtExceptionHappened(Thread thread, Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("AndroidRuntime", "App crashed");
                    }
                });
            }

            @Override
            protected void onBandageExceptionHappened(Throwable throwable) {
                throwable.printStackTrace();//打印警告级别log，该throwable可能是最开始的bug导致的，无需关心
                Log.e("AndroidRuntime", "Cockroach Worked");
            }

            @Override
            protected void onEnterSafeMode() {
                Log.e("AndroidRuntime", "onEnterSafeMode");
            }

            @Override
            protected void onMayBeBlackScreen(Throwable e) {
                super.onMayBeBlackScreen(e);
                Thread thread = Looper.getMainLooper().getThread();
                Log.e("AndroidRuntime", "--->onUncaughtExceptionHappened:" + thread + "<---", e);
                //黑屏时建议直接杀死app
                sysExcepHandler.uncaughtException(thread, new RuntimeException("black screen"));
            }
        });


    }
}
