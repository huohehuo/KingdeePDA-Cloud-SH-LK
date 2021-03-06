package com.fangzuo.assist.cloud.Utils;

import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 王璐阳 on 2017/11/30.
 */

public class EventBusUtil {
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
//        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(ClassEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(ClassEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
