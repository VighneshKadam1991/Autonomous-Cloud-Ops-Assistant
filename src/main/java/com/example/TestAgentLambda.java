package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class TestAgentLambda implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {

        CloudWatchSensor sensor = new CloudWatchSensor();
        AgentMemory memory = new AgentMemory(System.getenv("MEMORY_TABLE"));
        DecisionEngine engine = new HeuristicDecisionEngine();
        ActionExecutor executor = new ActionExecutor();

        AgentContext agentContext = AgentContextBuilder.build(sensor.observe(), memory);

        Decision decision = engine.decide(agentContext);

        executor.execute(decision);

        memory.save(agentContext, decision);

        return "Agent cycle completed";
    }
}