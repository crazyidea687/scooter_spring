package com.mahf.tcpscooter.component;

import org.springframework.stereotype.Component;

import java.nio.channels.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class DeviceManager {
    private static final Map<String, DeviceInfo> devices = new ConcurrentHashMap<>();

    // تحديث حالة الجهاز
    public static void updateDeviceStatus(String deviceIP, boolean isConnected) {
        if (isConnected) {
            devices.put(deviceIP, new DeviceInfo(deviceIP, true));
        } else {
            devices.remove(deviceIP);
        }
    }

    public static Channel getDeviceChannel(String deviceIp){
//        Channel device = devices.get(deviceIp).;
        return null;
    }
}
