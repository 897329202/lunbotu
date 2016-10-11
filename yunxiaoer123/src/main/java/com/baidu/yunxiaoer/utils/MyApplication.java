package com.baidu.yunxiaoer.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class MyApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int     mainThreadId;
    private static MyApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化context对象
        context = getApplicationContext();
        //初始化handler
        handler = new Handler();
        //获取主线程的线程id  myTid()在哪个方法被调用，返回的就是那个方法所在的线程id
        mainThreadId = android.os.Process.myTid();
    }
    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}



