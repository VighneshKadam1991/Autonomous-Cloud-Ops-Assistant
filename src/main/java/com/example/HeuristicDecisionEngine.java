package com.example;

public class HeuristicDecisionEngine implements DecisionEngine {
    @Override
    public Decision decide(AgentContext ctx) {

        if (ctx.cpuUtilization > 75 && ctx.restartFailures == 0)
            return new Decision("RESTART_INSTANCE", "Restart chosen based on memory");

        if (ctx.cpuUtilization > 75 && ctx.restartFailures > 0)
            return new Decision("SCALE_OUT", "Scale out chosen due to previous restart failure");

        return new Decision("WAIT", "CPU low, no action");
    }
}