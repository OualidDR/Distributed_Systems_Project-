package com.monitor.ui.desktop.controller;

import com.monitor.ui.desktop.view.DashboardView;
import com.monitor.ui.desktop.rmi.RMIServiceProxy;
import javax.swing.Timer;

public class DashboardController {
    private DashboardView view;
    private RMIServiceProxy proxy;

    public void launch() {
        proxy = new RMIServiceProxy();
        view = new DashboardView(this);
        view.show();
        Timer timer = new Timer(5000, e -> loadData());
        timer.start();
        loadData();
    }

    public void loadData() {
        try {
            var latest = proxy.getLatestMetrics();
            var agents = proxy.getAgentList();
            view.updateAgentTable(agents);
            view.updateCharts(latest);
        } catch (Exception e) {
            System.err.println("Erreur chargement données : " + e.getMessage());
        }
    }
}
