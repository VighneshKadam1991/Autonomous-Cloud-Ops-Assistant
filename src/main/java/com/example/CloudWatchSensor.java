package com.example;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

public class CloudWatchSensor {

    public AgentObservation observe() {

        // Simulated CPU for MVP demo
        double cpu = Math.random() * 100;

        return new AgentObservation("i-1234567890", cpu);
    }
}