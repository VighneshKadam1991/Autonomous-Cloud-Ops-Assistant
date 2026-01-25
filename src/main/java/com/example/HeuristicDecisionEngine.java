package com.example;

public class HeuristicDecisionEngine implements DecisionEngine {

    @Override
    public Decision decide(AgentContext context) {

        if (context.cpuUtilization > 80 && context.seenBefore) {
            return Decision.REBOOT_INSTANCE;
        }

        if (context.cpuUtilization > 80) {
            return Decision.LOG_ONLY;
        }

        return Decision.NO_ACTION;
    }
}