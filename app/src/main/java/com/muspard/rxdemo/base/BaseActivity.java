package com.muspard.rxdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;

import com.muspard.rxdemo.event.CommonEvent;
import com.muspard.rxdemo.event.Whats;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-04-12.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static Handler mUiHandler = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLayout();
        setContentView(inflateLayout());
        ButterKnife.bind(this);
        initData();
        initView();
        initSetting();
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventReceived(CommonEvent event) {
        onBgEvent(((Whats.EventWhats) event.eventWhats), event.msg, event.obj);
    }

    protected void onBgEvent(Whats.EventWhats eventWhats, String msg, Object obj) {
    }

    ;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUiEventReceived(CommonEvent event) {
        onUiEvent(((Whats.EventWhats) event.eventWhats), event.msg, event.obj);
    }

    protected void onUiEvent(Whats.EventWhats eventWhats, String msg, Object obj) {
    }

    ;


    public Handler getUiHandler() {
        if (mUiHandler == null) {
            mUiHandler = new Handler(this.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    handleMessages(msg.what, msg);
                }
            };
        }
        return mUiHandler;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void handleMessages(int what, Message msg) {
    }


    protected void beforeLayout() {
    }


    public abstract int inflateLayout();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initSetting();


}
