package com.muspard.rxdemo.event;

/**
 * Created by GUO on 2017/6/5.
 */

public class CommonEvent {

    public Whats.EventWhats eventWhats;
    public Whats.MsgWhats msgWhats;
    public String msg;
    public Object obj;

    public CommonEvent(Whats whats) {
        this.eventWhats = (Whats.EventWhats) whats;
        this.msgWhats = (Whats.MsgWhats) whats;
    }

    public CommonEvent(Whats whats, String msg) {
        this.eventWhats = (Whats.EventWhats) whats;
        this.msgWhats = (Whats.MsgWhats) whats;

        this.msg = msg;
    }

    public CommonEvent(Whats whats, String msg, Object event) {
        this.eventWhats = (Whats.EventWhats) whats;
        this.msgWhats = (Whats.MsgWhats) whats;
        this.msg = msg;
        this.obj = event;
    }

}
