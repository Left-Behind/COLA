package com.azhu.apocalypse.dto.domainevent;

import lombok.Data;

@Data
public class MetricItemCreatedEvent {

    private String id;

    private String userId;

    private String mainMetricType;
}
