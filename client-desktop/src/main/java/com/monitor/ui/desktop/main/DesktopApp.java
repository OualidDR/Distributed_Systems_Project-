package com.monitor.ui.desktop.main;

import com.monitor.ui.desktop.controller.DashboardController;
import javax.swing.*;

public class DesktopApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardController controller = new DashboardController();
            controller.launch();
        });
    }
}
