package com.curso.algasensors.temperature.monitoring.infrastructure.rabbitmq;

import com.curso.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.curso.algasensors.temperature.monitoring.domain.service.SensorAlertService;
import com.curso.algasensors.temperature.monitoring.domain.service.TemperatureMonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.curso.algasensors.temperature.monitoring.infrastructure.rabbitmq.RabbitMQConfig.QUEUE_PROCESS_ALERTS;
import static com.curso.algasensors.temperature.monitoring.infrastructure.rabbitmq.RabbitMQConfig.QUEUE_PROCESS_TEMPERATURE;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;

    private final SensorAlertService sensorAlertService;

    @RabbitListener(queues = QUEUE_PROCESS_TEMPERATURE, concurrency = "2-4")
    public void processTemperatureHandler(@Payload TemperatureLogData temperatureLogData) {
        temperatureMonitoringService.processTemperatureReading(temperatureLogData);
    }

    @RabbitListener(queues = QUEUE_PROCESS_ALERTS, concurrency = "2-4")
    public void processAlertHandler(@Payload TemperatureLogData temperatureLogData) {
        sensorAlertService.handlerSensorAlert(temperatureLogData);
    }
}
