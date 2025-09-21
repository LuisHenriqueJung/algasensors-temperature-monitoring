package com.curso.algasensors.temperature.monitoring.infrastructure.rabbitmq;

import com.curso.algasensors.temperature.monitoring.api.model.TemperatureLogData;
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
    @RabbitListener(queues =QUEUE_NAME)
    public void handler(@Payload TemperatureLogData temperatureLogData, @Headers Map<String, Object> headers) {
        log.info("Received message: {} from sensor: {}", temperatureLogData, headers);
        log.info("Received message: {}", temperatureLogData);
    }
}
