package com.example;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

import java.time.Instant;
import java.util.List;

public class CloudWatchSensor {

    private final CloudWatchClient cloudWatch =
            CloudWatchClient.builder().build();

    public AgentObservation observe() {

        String instanceId = "i-1234567890"; // replace with real EC2 ID
        Instant endTime = Instant.now();
        Instant startTime = endTime.minusSeconds(300); // last 5 minutes

        Metric metric = Metric.builder()
                .namespace("AWS/EC2")
                .metricName("CPUUtilization")
                .dimensions(
                        Dimension.builder()
                                .name("InstanceId")
                                .value(instanceId)
                                .build()
                )
                .build();

        MetricStat metricStat = MetricStat.builder()
                .metric(metric)
                .period(300)
                .stat("Average")
                .build();

        MetricDataQuery query = MetricDataQuery.builder()
                .id("cpu")
                .metricStat(metricStat)
                .returnData(true)
                .build();

        GetMetricDataRequest request = GetMetricDataRequest.builder()
                .startTime(startTime)
                .endTime(endTime)
                .metricDataQueries(query)
                .build();

        GetMetricDataResponse response =
                cloudWatch.getMetricData(request);

        double cpu = response.metricDataResults().isEmpty()
                || response.metricDataResults().get(0).values().isEmpty()
                ? 0.0
                : response.metricDataResults().get(0).values().get(0);

        return new AgentObservation(instanceId, cpu);
    }
}