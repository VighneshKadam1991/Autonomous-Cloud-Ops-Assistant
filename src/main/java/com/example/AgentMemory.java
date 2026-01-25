package com.example;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.Instant;

public class AgentMemory {

    private final String tableName;

    public AgentMemory(String tableName) {
        this.tableName = tableName;
    }

    // MVP stub (replace with DynamoDB GetItem)
    public boolean exists(String resourceId) {
        return Math.random() > 0.5;
    }

    // MVP stub (replace with DynamoDB PutItem)
    public void save(AgentContext context, Decision decision) {
        System.out.println(
                "MEMORY: Saved [" + context.resourceId +
                        "] decision=" + decision
        );
    }
}