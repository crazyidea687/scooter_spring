package com.mahf.tcpscooter.component;

import org.springframework.stereotype.Component;


  class DeviceInfo {
     private String deviceIP;
     private boolean isConnected;

     public DeviceInfo(String deviceIP, boolean isConnected) {
         this.deviceIP = deviceIP;
         this.isConnected = isConnected;
     }
}
