package com.example.hello.myapplication.application;


import android.app.Application;

import com.example.hello.myapplication.comm.CustomerCrashHandler;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CustomerCrashHandler customerCrashHandler = new CustomerCrashHandler();
        customerCrashHandler.setCustomCrashHandler(getApplicationContext());
    }
}
