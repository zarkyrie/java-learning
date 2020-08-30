package com.ljh.activitidemo.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventNotificationCallUtil {

    private static Boolean caFlag;

    @Value("${custom.ca.mode.enable:false}")
    public void setCaFlag(Boolean caFlag) {
        EventNotificationCallUtil.caFlag = caFlag;
    }

    synchronized public static void getInstance() {
        if (!caFlag) {
            System.out.println("1111");
        } else {
            System.out.println("222");
        }
    }
}
