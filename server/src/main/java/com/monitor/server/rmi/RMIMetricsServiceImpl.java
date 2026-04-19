package com.monitor.server.rmi;

import com.monitor.server.core.ConcurrentDataStore;
import com.monitor.shared.model.MetricData;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class RMIMetricsServiceImpl extends UnicastRemoteObject implements RMIMetricsService {
    private final ConcurrentDataStore store;

    public RMIMetricsServiceImpl(ConcurrentDataStore store) throws RemoteException {
        this.store = store;
    }

    @Override public Map<String, MetricData> getLatestMetrics() { return store.getLatest(); }
    @Override public List<MetricData> getHistory(String agentId) { return store.getHistory(agentId); }
    @Override public List<String> getAgentList() { return new ArrayList<>(store.getAgentIds()); }
}
