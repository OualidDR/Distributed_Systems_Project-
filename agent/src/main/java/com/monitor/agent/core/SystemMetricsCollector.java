package com.monitor.agent.core;

import com.monitor.shared.model.MetricData;
import com.monitor.shared.utils.Logger;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;

public class SystemMetricsCollector {
    private final SystemInfo si = new SystemInfo();

    public MetricData collect(String agentId) {
        // CPU
        CentralProcessor cpu = si.getHardware().getProcessor();
        double cpuUsage = cpu.getSystemCpuLoad(500) * 100;

        // RAM
        GlobalMemory mem = si.getHardware().getMemory();
        double ramUsage = (1.0 - (double) mem.getAvailable() / mem.getTotal()) * 100;

        // Disque
        FileSystem fs = si.getOperatingSystem().getFileSystem();
        double diskUsage = fs.getFileStores().stream()
            .mapToDouble(s -> (1.0 - (double) s.getUsableSpace() / s.getTotalSpace()) * 100)
            .average()
            .orElse(0.0);

        Logger.info("MetricsCollector",
            String.format("CPU=%.1f%% RAM=%.1f%% Disk=%.1f%%", cpuUsage, ramUsage, diskUsage));

        return new MetricData(agentId, cpuUsage, ramUsage, diskUsage, System.currentTimeMillis());
    }
}