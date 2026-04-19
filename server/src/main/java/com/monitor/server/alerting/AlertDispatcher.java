package com.monitor.server.alerting;

import com.monitor.shared.model.Alert;

public class AlertDispatcher {
    public void dispatch(Alert alert) {
        System.out.println("[ALERTE] " + alert.severity() + " sur agent " + alert.agentId() + " — type: " + alert.type());
        // TODO: notifier WebSocket, email, etc.
    }
}
