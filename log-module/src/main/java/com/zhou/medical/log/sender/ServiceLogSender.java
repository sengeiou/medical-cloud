package com.zhou.medical.log.sender;

import com.zhou.medical.log.config.RabbitConfig;
import com.zhou.medical.log.entity.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLogSender {

    @Autowired
    private RabbitMqSender<ServiceLog> rabbitMqSender;

    public void send(ServiceLog serviceLog) {
        rabbitMqSender.send(RabbitConfig.EXCHANGE_SERVICE_LOG,RabbitConfig.ROUTING_KEY_SERVICE_LOG, serviceLog);
    }
}