package com.zhou.medical.log.sender;

import com.zhou.medical.log.config.RabbitConfig;
import com.zhou.medical.log.entity.RequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestLogSender{

    @Autowired
    private RabbitMqSender<RequestLog> rabbitMqSender;

    public void send(RequestLog requestLog) {
        rabbitMqSender.send(RabbitConfig.EXCHANGE_REQUEST_LOG,RabbitConfig.ROUTING_KEY_REQUEST_LOG, requestLog);
    }
}
