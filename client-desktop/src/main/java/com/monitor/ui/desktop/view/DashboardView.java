package com.monitor.ui.desktop.view;

import com.monitor.ui.desktop.controller.DashboardController;
import com.monitor.shared.model.MetricData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DashboardView {
    private JFrame frame;
    private JTable agentTable;
    private DefaultTableModel tableModel;
    private final DashboardController controller;

    public DashboardView(DashboardController controller) {
        this.controller = controller;
    }

    public void show() {
        frame = new JFrame("Système de Surveillance Distribué");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        tableModel = new DefaultTableModel(new String[]{"Agent ID", "CPU %", "RAM %", "Disque %", "Timestamp"}, 0);
        agentTable = new JTable(tableModel);

        frame.add(new JScrollPane(agentTable), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void updateAgentTable(List<String> agents) {
        // Rafraîchissement géré via updateCharts
    }

    public void updateCharts(Map<String, MetricData> data) {
        tableModel.setRowCount(0);
        data.forEach((id, m) -> tableModel.addRow(new Object[]{
            m.agentId(),
            String.format("%.1f", m.cpuUsage()),
            String.format("%.1f", m.ramUsage()),
            String.format("%.1f", m.diskUsage()),
            m.timestamp()
        }));
    }
}
