package com.example;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

public class CloudWatchSensor {

    private static final CloudWatchClient cw = CloudWatchClient.create();

    public static AgentObservation observe(String instanceId) {
        // Simplified: fetch average CPU over last 5 minutes
        MetricDataQuery query = MetricDataQuery.builder()
                .id("cpuQuery")
                .metricStat(MetricStat.builder()
                        .metric(Metric.builder()
                                .metricName("CPUUtilization")
                                .namespace("AWS/EC2")
                                .dimensions(Dimension.builder().name("InstanceId").value(instanceId).build())
                                .build())
                        .period(300)
                        .stat("Average")
                        .build())
                .returnData(true)
                .build();

        GetMetricDataResponse response = cw.getMetricData(GetMetricDataRequest.builder()
                .metricDataQueries(query)
                .build());

        double cpu = response.metricDataResults().get(0).values().isEmpty() ?
                0 : response.metricDataResults().get(0).values().get(0);

        AgentObservation obs = new AgentObservation();
        obs.instanceId = instanceId;
        obs.cpuUtilization = cpu;
        obs.highCpuDurationMinutes = cpu > 75 ? 5 : 0;
        return obs;
    }
}