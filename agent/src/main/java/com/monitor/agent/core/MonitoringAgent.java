package com.monitor.agent.core;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.monitor.agent.threads.MetricPublisherTask;
import com.monitor.shared.constants.ThresholdConstants;
import com.monitor.shared.utils.Logger;

public class MonitoringAgent {
    private final String agentId = UUID.randomUUID().toString();
    private final ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(2);

    public void start() {
        Logger.info("MonitoringAgent", "Démarrage agent ID = " + agentId);
        MetricPublisherTask task = new MetricPublisherTask(agentId);
        scheduler.scheduleAtFixedRate(
            task,
            0,
            ThresholdConstants.AGENT_SEND_INTERVAL_MS,
            TimeUnit.MILLISECONDS
        );
    }

    public void stop() {
        scheduler.shutdown();
    }
}