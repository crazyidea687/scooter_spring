package com.mahf.tcpscooter.controllers;

import com.mahf.tcpscooter.server.TcpServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    private final TcpServerHandler tcpServerHandler;

    public MessageController(TcpServerHandler tcpServerHandler) {
        this.tcpServerHandler = tcpServerHandler;
    }

    @PostMapping("/messages")
    public void receiveMessage(@RequestBody String message) {
        // معالجة الرسالة وإرسالها إلى الأجهزة IoT
        String deviceIP = "192.168.1.100"; // تحديد عنوان IP للجهاز
        tcpServerHandler.sendMessageToDevice(deviceIP, message);
    }

    // يجب أن تضيف نقطة النهاية لإزالة الأجهزة عند فقدها
}
