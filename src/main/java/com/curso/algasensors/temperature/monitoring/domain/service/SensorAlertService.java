package com.curso.algasensors.temperature.monitoring.domain.service;

import com.curso.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.curso.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.curso.algasensors.temperature.monitoring.domain.model.SensorId;
import com.curso.algasensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorAlertService {

    private final SensorAlertRepository sensorAlertRepository;

    @Transactional
    public void handlerSensorAlert(TemperatureLogData temperatureLogData) {
            sensorAlertRepository.findById(new SensorId(temperatureLogData.getSensorId()))
                    .ifPresentOrElse(alert -> handlerSensorAlert(temperatureLogData, alert), () -> logIgnoredTemperature(temperatureLogData));
        }

        private void logIgnoredTemperature (TemperatureLogData temperatureLogData){
            log.info("Ignored temperature: SensorID {} temp {} at {}", temperatureLogData.getSensorId(), temperatureLogData.getValue(), OffsetDateTime.now());
        }

        private void handlerSensorAlert (TemperatureLogData temperatureLogData, SensorAlert alert){
            if(alert.getMaxTemperature() != null && temperatureLogData.getValue() > alert.getMaxTemperature()) {
                log.info("Alert: SensorID {} temp {} ", temperatureLogData.getSensorId(), temperatureLogData.getValue());
            }else if(alert.getMinTemperature() != null && temperatureLogData.getValue() < alert.getMinTemperature()) {
                log.info("Alert: SensorID {} temp {} ", temperatureLogData.getSensorId(), temperatureLogData.getValue());
            }else{
                logIgnoredTemperature(temperatureLogData);
            }
        }
    }


