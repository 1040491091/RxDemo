package com.muspard.rxdemo;

import android.app.Application;
import android.content.Context;

import com.vondear.rxtools.RxTool;

/**
 * Created by Administrator on 2018-04-12.
 */

public class MyApplication extends Application {
    public static Application mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        RxTool.init(mInstance);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }
}
