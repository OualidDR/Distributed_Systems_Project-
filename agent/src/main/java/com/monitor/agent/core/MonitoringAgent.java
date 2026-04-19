package com.monitor.agent.core;

import com.monitor.agent.threads.MetricPublisherTask;
import com.monitor.shared.constants.Constants;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MonitoringAgent {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final String id = java.util.UUID.randomUUID().toString();

    public void start() {
        System.out.println("Agent démarré : " + id);
        MetricPublisherTask task = new MetricPublisherTask(id);
        scheduler.scheduleAtFixedRate(task, 0, Constants.AGENT_SEND_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
