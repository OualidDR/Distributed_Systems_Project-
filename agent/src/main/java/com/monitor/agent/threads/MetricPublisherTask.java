package com.monitor.agent.threads;

import java.util.UUID;

import com.monitor.agent.core.SystemMetricsCollector;
import com.monitor.agent.network.TCPAlertClient;
import com.monitor.agent.network.UDPSender;
import com.monitor.shared.constants.ThresholdConstants;
import com.monitor.shared.model.Alert;
import com.monitor.shared.model.MetricData;
import com.monitor.shared.utils.Logger;

public class MetricPublisherTask implements Runnable {
    private final String agentId;
    private final SystemMetricsCollector collector = new SystemMetricsCollector();
    private final UDPSender udpSender = new UDPSender();
    private final TCPAlertClient tcpClient = new TCPAlertClient();

    public MetricPublisherTask(String agentId) {
        this.agentId = agentId;
    }

    @Override
    public void run() {
        try {
            // 1. Collecter
            MetricData data = collector.collect(agentId);

            // 2. Envoyer via UDP
            udpSender.send(data);

            // 3. Vérifier les seuils → alerte TCP si dépassé
            if (data.cpuUsage() > ThresholdConstants.CPU_CRITICAL) {
                sendAlert(data.agentId(), "CPU", "CRITICAL");
            }
            if (data.ramUsage() > ThresholdConstants.RAM_CRITICAL) {
                sendAlert(data.agentId(), "RAM", "CRITICAL");
            }
            if (data.diskUsage() > ThresholdConstants.DISK_CRITICAL) {
                sendAlert(data.agentId(), "DISK", "CRITICAL");
            }

        } catch (Exception e) {
            Logger.error("MetricPublisherTask", "Erreur : " + e.getMessage());
        }
    }

    private void sendAlert(String agentId, String type, String severity) {
        try {
            Alert alert = new Alert(
                UUID.randomUUID().toString(),
                agentId, type, severity,
                System.currentTimeMillis()
            );
            tcpClient.sendAlert(alert);
        } catch (Exception e) {
            Logger.error("MetricPublisherTask", "Échec envoi alerte TCP : " + e.getMessage());
        }
    }
}