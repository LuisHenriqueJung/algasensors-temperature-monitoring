package com.curso.algasensors.temperature.monitoring.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertOutput {

    private TSID id;
    private Double minTemperature;
    private Double maxTemperature;
}
