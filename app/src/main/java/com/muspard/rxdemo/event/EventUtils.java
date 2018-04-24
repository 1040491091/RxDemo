package com.muspard.rxdemo.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by GMD on 2017/7/28.
 */

public class EventUtils {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(CommonEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void sendMsgEvent(Whats.MsgWhats what, String msg, Object obj) {
        EventBus.getDefault().post(new CommonEvent(what, msg, obj));
    }

    public static void sendMsgEvent(Whats.MsgWhats what, String msg) {
        EventBus.getDefault().post(new CommonEvent(what, msg));
    }


    public static void sendEventEvent(Whats.EventWhats what, String msg, Object obj) {
        EventBus.getDefault().post(new CommonEvent(what, msg, obj));
    }

    public static void sendEventEvent(Whats.EventWhats what, String msg) {
        EventBus.getDefault().post(new CommonEvent(what, msg));
    }

    public static void sendEvent(Whats what) {
        EventBus.getDefault().post(new CommonEvent(what));
    }
}
