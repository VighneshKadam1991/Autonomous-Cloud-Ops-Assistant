package com.example;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.Instant;

public class AgentMemory {

    private static final String TABLE_NAME = "agent-memory";

    private static final DynamoDbClient client = DynamoDbClient.create();

    public static AgentMemoryRecord load(String instanceId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(java.util.Map.of("instanceId", AttributeValue.builder().s(instanceId).build()))
                .build();
        GetItemResponse response = client.getItem(request);
        if (!response.hasItem()) return new AgentMemoryRecord();
        AgentMemoryRecord record = new AgentMemoryRecord();
        record.instanceId = instanceId;
        record.lastAction = response.item().getOrDefault("lastAction", AttributeValue.builder().s("").build()).s();
        record.lastActionTime = response.item().containsKey("lastActionTime") ?
                Instant.parse(response.item().get("lastActionTime").s()) : Instant.EPOCH;
        record.restartFailures = Integer.parseInt(response.item().getOrDefault("restartFailures", AttributeValue.builder().n("0").build()).n());
        record.scaleSuccessCount = Integer.parseInt(response.item().getOrDefault("scaleSuccessCount", AttributeValue.builder().n("0").build()).n());
        return record;
    }

    public static void save(AgentMemoryRecord record) {
        client.putItem(PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(java.util.Map.of(
                        "instanceId", AttributeValue.builder().s(record.instanceId).build(),
                        "lastAction", AttributeValue.builder().s(record.lastAction).build(),
                        "lastActionTime", AttributeValue.builder().s(record.lastActionTime.toString()).build(),
                        "restartFailures", AttributeValue.builder().n(Integer.toString(record.restartFailures)).build(),
                        "scaleSuccessCount", AttributeValue.builder().n(Integer.toString(record.scaleSuccessCount)).build()
                ))
                .build());
    }
}