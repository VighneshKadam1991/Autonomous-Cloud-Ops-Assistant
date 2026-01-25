package com.example;


public class AgentObservation {

    private final String resourceId;
    private final double cpu;

    public AgentObservation(String resourceId, double cpu) {
        this.resourceId = resourceId;
        this.cpu = cpu;
    }

    public String getResourceId() {
        return resourceId;
    }

    public double getCpu() {
        return cpu;
    }
}