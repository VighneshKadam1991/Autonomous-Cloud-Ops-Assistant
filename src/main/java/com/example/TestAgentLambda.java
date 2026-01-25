package com.example;

import com.amazonaws.services.lambda.runtime.Context;

import java.util.Map;

public class TestAgentLambda {

    private final DecisionEngine agent = new HeuristicDecisionEngine();

    public String handleRequest(Map<String,Object> event, Context context) {

        String instanceId = (String) event.get("instanceId");

        AgentObservation obs = CloudWatchSensor.observe(instanceId);
        AgentMemoryRecord memory = AgentMemory.load(instanceId);

        AgentContext ctx = AgentContextBuilder.fromObservationAndMemory(obs, memory);

        Decision decision = agent.decide(ctx);

        ActionExecutor.execute(decision.action, instanceId);

        memory.lastAction = decision.action;
        memory.lastActionTime = java.time.Instant.now();
        if ("RESTART_INSTANCE".equals(decision.action)) memory.restartFailures++;
        if ("SCALE_OUT".equals(decision.action)) memory.scaleSuccessCount++;
        AgentMemory.save(memory);

        return "Action: " + decision.action + " | Reason: " + decision.reason;
    }
}