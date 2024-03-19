package com.mahf.tcpscooter.server;

import com.mahf.tcpscooter.component.DeviceManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    // Map to store device IP addresses and their corresponding channels
    private static final Map<String, Channel> deviceChannels = new ConcurrentHashMap<>();


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String deviceIP = ctx.channel().remoteAddress().toString();
        System.out.println(deviceIP);
        DeviceManager.updateDeviceStatus(deviceIP, true);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String deviceIP = ctx.channel().remoteAddress().toString();
        DeviceManager.updateDeviceStatus(deviceIP, false);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        try {
            String message = buf.toString(StandardCharsets.UTF_8);
            System.out.println("Received message from client: " + message);
            // Process the message here, e.g., parse and extract information
        } finally {
            buf.release();
        }
    }


    public static void sendMessageToDevice(String deviceIP, String message) {
        // إرسال الرسالة إلى جهاز محدد
        System.out.println(deviceIP);
        Channel channel = deviceChannels.get(deviceIP);
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(Unpooled.copiedBuffer(message.getBytes()));
        } else {
            // Handle case where device is not connected or channel is not active
            System.out.println("Device " + deviceIP + " is not connected or channel is not active");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // معالجة الاستثناءات
        cause.printStackTrace();
        ctx.close();
    }
}
