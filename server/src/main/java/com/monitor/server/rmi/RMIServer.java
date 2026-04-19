package com.monitor.server.rmi;

import com.monitor.server.core.ConcurrentDataStore;
import com.monitor.shared.constants.Constants;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void start(ConcurrentDataStore store) throws Exception {
        Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
        RMIMetricsServiceImpl service = new RMIMetricsServiceImpl(store);
        registry.rebind(Constants.RMI_SERVICE_NAME, service);
        System.out.println("RMI Registry démarré sur port " + Constants.RMI_PORT);
    }
}
