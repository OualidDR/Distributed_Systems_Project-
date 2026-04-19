package com.monitor.server.rmi;

import com.monitor.shared.model.MetricData;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface RMIMetricsService extends Remote {
    Map<String, MetricData> getLatestMetrics() throws RemoteException;
    List<MetricData> getHistory(String agentId) throws RemoteException;
    List<String> getAgentList() throws RemoteException;
}
