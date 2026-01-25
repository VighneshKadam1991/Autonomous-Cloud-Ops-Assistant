package com.example;

import java.time.Instant;

public class AgentMemoryRecord {

    public String instanceId;
    public String lastAction;
    public Instant lastActionTime;
    public int restartFailures;
    public int scaleSuccessCount;
}
