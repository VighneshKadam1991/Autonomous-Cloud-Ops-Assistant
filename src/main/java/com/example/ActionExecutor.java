package com.example;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.RebootInstancesRequest;

public class ActionExecutor {

    public void execute(Decision decision) {

        switch (decision) {
            case REBOOT_INSTANCE ->
                    System.out.println("Rebooting EC2 instance");
            case LOG_ONLY ->
                    System.out.println("High CPU detected, monitoring");
            default ->
                    System.out.println("All normal");
        }
    }
}
