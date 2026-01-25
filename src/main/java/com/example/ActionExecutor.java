package com.example;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.RebootInstancesRequest;

public class ActionExecutor {

    private static final Ec2Client ec2 = Ec2Client.create();

    public static void execute(String action, String instanceId) {
        switch(action) {
            case "RESTART_INSTANCE":
                ec2.rebootInstances(RebootInstancesRequest.builder()
                        .instanceIds(instanceId)
                        .build());
                break;
            case "SCALE_OUT":
                System.out.println("Scaling out ASG - implement your scaling logic here");
                break;
            case "WAIT":
                break;
        }
    }
}