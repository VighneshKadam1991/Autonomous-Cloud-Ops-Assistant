package com.example;

import java.util.Map;

public class AgentContextBuilder {
    public static AgentContext fromObservationAndMemory(AgentObservation obs, AgentMemoryRecord mem) {
        AgentContext ctx = new AgentContext();
        ctx.cpuUtilization = obs.cpuUtilization;
        ctx.highCpuDurationMinutes = obs.highCpuDurationMinutes;
        ctx.restartFailures = mem.restartFailures;
        ctx.scaleSuccessCount = mem.scaleSuccessCount;
        ctx.historicalSuccessRates = Map.of(
                "RESTART", 1.0 - (mem.restartFailures / 10.0),
                "SCALE", mem.scaleSuccessCount / 10.0
        );
        return ctx;
    }
}