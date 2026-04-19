package com.monitor.agent.threads;

import com.monitor.agent.core.SystemMetricsCollector;
import com.monitor.agent.network.TCPAlertClient;
import com.monitor.agent.network.UDPSender;
import com.monitor.shared.constants.Constants;
import com.monitor.shared.model.Alert;
import com.monitor.shared.model.MetricData;
import java.util.UUID;

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
            MetricData data = collector.collect(agentId);
            udpSender.send(data);

            if (data.cpuUsage() > Constants.CPU_ALERT_THRESHOLD) {
                Alert alert = new Alert(UUID.randomUUID().toString(), agentId, "CPU", "CRITICAL", System.currentTimeMillis());
                tcpClient.sendAlert(alert);
            }
        } catch (Exception e) {
            System.err.println("Erreur envoi métriques : " + e.getMessage());
        }
    }
}
