package com.example;

import com.example.AgentContext;
import com.example.AgentMemory;
import com.example.AgentObservation;

public class AgentContextBuilder {

    public static AgentContext build(AgentObservation obs, AgentMemory memory) {
        AgentContext ctx = new AgentContext();
        ctx.resourceId = obs.resourceId();
        ctx.cpuUtilization = obs.cpu();
        ctx.seenBefore = memory.exists(obs.resourceId());
        return ctx;
    }
}