package com.curso.algasensors.temperature.monitoring.infrastructure.rabbitmq;

import com.curso.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.curso.algasensors.temperature.monitoring.domain.service.TemperatureMonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.curso.algasensors.temperature.monitoring.infrastructure.rabbitmq.RabbitMQConfig.QUEUE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;
    @RabbitListener(queues =QUEUE_NAME, concurrency = "2-4")
    public void handler(@Payload TemperatureLogData temperatureLogData) throws InterruptedException {
        temperatureMonitoringService.processTemperatureReading(temperatureLogData);
        Thread.sleep(3000);
    }
}
