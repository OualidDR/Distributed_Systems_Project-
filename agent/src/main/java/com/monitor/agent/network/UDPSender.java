package com.monitor.agent.network;

import com.monitor.shared.model.MetricData;
import com.monitor.shared.utils.SerializationUtils;
import com.monitor.shared.constants.Constants;
import java.net.*;

public class UDPSender {
    public void send(MetricData data) throws Exception {
        byte[] bytes = SerializationUtils.serialize(data);
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, Constants.UDP_PORT);
        socket.send(packet);
        socket.close();
    }
}
