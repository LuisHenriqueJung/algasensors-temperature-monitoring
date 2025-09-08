package com.curso.algasensors.temperature.monitoring.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SensorAlert {

    @Id
    @AttributeOverride(name = "value",
            column = @Column(name = "id", columnDefinition = "bigint"))
    private SensorId id;
    private Double maxTemperature;
    private Double minTemperature;
}
