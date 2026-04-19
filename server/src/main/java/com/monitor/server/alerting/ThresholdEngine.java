package com.monitor.server.alerting;

import com.monitor.shared.constants.Constants;
import com.monitor.shared.model.Alert;
import com.monitor.shared.model.MetricData;
import java.util.UUID;

public class ThresholdEngine {
    public Alert check(MetricData data) {
        if (data.cpuUsage() > Constants.CPU_ALERT_THRESHOLD)
            return new Alert(UUID.randomUUID().toString(), data.agentId(), "CPU", "CRITICAL", System.currentTimeMillis());
        if (data.ramUsage() > Constants.RAM_ALERT_THRESHOLD)
            return new Alert(UUID.randomUUID().toString(), data.agentId(), "RAM", "WARNING", System.currentTimeMillis());
        if (data.diskUsage() > Constants.DISK_ALERT_THRESHOLD)
            return new Alert(UUID.randomUUID().toString(), data.agentId(), "DISK", "WARNING", System.currentTimeMillis());
        return null;
    }
}
