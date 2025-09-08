package com.curso.algasensors.temperature.monitoring.api.model;

import lombok.Data;

@Data
public class SensorAlertInput {
    private Double minTemperature;
    private Double maxTemperature;
}
