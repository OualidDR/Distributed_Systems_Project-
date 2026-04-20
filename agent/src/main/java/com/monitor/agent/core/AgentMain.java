package com.monitor.agent.core;

public class AgentMain {
    public static void main(String[] args) {
        MonitoringAgent agent = new MonitoringAgent();
        agent.start();

        // Arrêt propre avec Ctrl+C
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            agent.stop();
            System.out.println("Agent arrêté.");
        }));
    }
}