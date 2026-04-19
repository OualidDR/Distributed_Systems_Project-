package com.monitor.shared.model;

import java.io.Serializable;

public record Alert(
    String id,
    String agentId,
    String type,
    String severity,
    long timestamp
) implements Serializable {}
