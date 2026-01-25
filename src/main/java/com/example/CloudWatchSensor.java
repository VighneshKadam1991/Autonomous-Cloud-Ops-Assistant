package com.example;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

public class CloudWatchSensor {

    public AgentObservation observe() {

        // Mocked for MVP demo (you can later wire real metrics)
        double cpu = Math.random() * 100;

        return new AgentObservation("i-123456", cpu);
    }
}