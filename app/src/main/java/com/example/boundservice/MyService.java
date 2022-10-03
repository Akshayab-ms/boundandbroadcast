package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    ScheduledExecutorService myschedule_executor;

    private IBinder binder = new Binder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        myschedule_executor= Executors.newScheduledThreadPool(1);
        myschedule_executor.scheduleAtFixedRate(new Runnable(){

            @Override
            public void run() {
                MainActivity.tv.setText("currenttime:"+new Date());

            }

        },1,1,TimeUnit.SECONDS);
        return binder;
    }
    public boolean onunbind(Intent intent){
        myschedule_executor.shutdown();
        return  super.onUnbind(intent);
    }
}
