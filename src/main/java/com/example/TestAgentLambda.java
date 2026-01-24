package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

public class TestAgentLambda implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        String userMessage = event.getOrDefault("message", "Hello from AI Test Agent!");
        // Here you would call AWS Bedrock / Comprehend for agentic logic
        return "AI Response: " + userMessage;
    }
}
