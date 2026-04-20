package com.monitor.agent.network;

import com.monitor.shared.constants.NetworkConstants;
import com.monitor.shared.model.MetricData;
import com.monitor.shared.utils.Logger;
import com.monitor.shared.utils.SerializationUtils;
import java.net.*;

public class UDPSender {
    public void send(MetricData data) throws Exception {
        byte[] bytes = SerializationUtils.serialize(data);
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(NetworkConstants.SERVER_HOST);
            DatagramPacket packet = new DatagramPacket(
                bytes, bytes.length, address, NetworkConstants.PORT_UDP
            );
            socket.send(packet);
            Logger.info("UDPSender", "MetricData envoyée à " + NetworkConstants.SERVER_HOST);
        }
    }
}