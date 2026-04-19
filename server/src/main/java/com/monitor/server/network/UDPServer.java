package com.monitor.server.network;

import com.monitor.server.core.ConcurrentDataStore;
import com.monitor.shared.model.MetricData;
import com.monitor.shared.utils.SerializationUtils;
import com.monitor.shared.constants.Constants;
import java.net.*;

public class UDPServer implements Runnable {
    private final ConcurrentDataStore store;

    public UDPServer(ConcurrentDataStore store) { this.store = store; }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(Constants.UDP_PORT)) {
            byte[] buffer = new byte[4096];
            System.out.println("UDP Server en écoute sur port " + Constants.UDP_PORT);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                MetricData data = (MetricData) SerializationUtils.deserialize(packet.getData());
                store.save(data);
                System.out.println("Reçu de " + data.agentId() + " CPU=" + String.format("%.1f", data.cpuUsage()) + "%");
            }
        } catch (Exception e) {
            System.err.println("Erreur UDP : " + e.getMessage());
        }
    }
}
