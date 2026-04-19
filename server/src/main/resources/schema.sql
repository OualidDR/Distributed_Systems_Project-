CREATE TABLE IF NOT EXISTS metric_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    agent_id VARCHAR(100),
    cpu_usage DOUBLE,
    ram_usage DOUBLE,
    disk_usage DOUBLE,
    timestamp BIGINT
);

CREATE TABLE IF NOT EXISTS alerts (
    id VARCHAR(100) PRIMARY KEY,
    agent_id VARCHAR(100),
    type VARCHAR(50),
    severity VARCHAR(50),
    timestamp BIGINT
);
