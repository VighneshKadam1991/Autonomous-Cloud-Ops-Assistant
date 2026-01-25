package com.example;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.Instant;

public class AgentMemory {

    private final DynamoDbClient dynamo;
    private final String tableName;

    public AgentMemory(String tableName) {
        this.dynamo = DynamoDbClient.create();
        this.tableName = tableName;
    }

    public boolean exists(String resourceId) {
        // simplified for MVP
        return Math.random() > 0.5;
    }

    public void save(AgentContext context, Decision decision) {
        System.out.println("Saving memory for " + context.resourceId);
    }
}
