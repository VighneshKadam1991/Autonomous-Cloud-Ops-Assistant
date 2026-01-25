package com.example;

import java.util.Map;

public class AgentContext {
    public double cpuUtilization;
    public int highCpuDurationMinutes;
    public int restartFailures;
    public int scaleSuccessCount;
    public Map<String, Double> historicalSuccessRates;
}